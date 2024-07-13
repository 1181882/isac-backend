package backend.isac.service;

import backend.isac.model.User;
import backend.isac.model.UserVerificationToken;
import backend.isac.model.enums.ERole;
import backend.isac.repository.UserRepository;
import backend.isac.repository.UserVerificationTokenRepository;
import backend.isac.security.dto.AuthLoginDTO;
import backend.isac.security.dto.AuthRegisterDTO;
import backend.isac.security.dto.AuthResponseDTO;
import backend.isac.security.dto.ResetPasswordDTO;
import backend.isac.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    // Login
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    // Register
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    // Cookie
    @Value("${auth.app.jwtCookie}")
    private String jwtCookie;
    @Value("${auth.app.jwtExpirationMin}")
    private int jwtExpirationMin;
    //
    @Autowired
    UserVerificationTokenRepository userVerificationTokenRepository;

    public AuthResponseDTO login(AuthLoginDTO loginRequest) {
        return basicLogin(loginRequest);
    }

    private AuthResponseDTO basicLogin(AuthLoginDTO loginRequest) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password);
        Authentication authentication = this.authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();

        // Atualizar lastLoginAt
        userDetails.setLastLoginAt(LocalDateTime.now());
        userRepository.save(userDetails);

        String name = userDetails.getFirstName() + " " + userDetails.getLastName();
        String email = userDetails.getEmail();
        ERole role = userDetails.getRole();
        return new AuthResponseDTO(name, email, jwt, role);
    }

    public boolean registerUser(AuthRegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.email)) {
            throw new IllegalArgumentException("Error: Email is already in use!");
        }
        if (!isValidPassword(registerDTO.password)) {
            throw new IllegalArgumentException("Error: invalid password!");
        }
        // Create new user
        User newUser = User.builder()
                .firstName(registerDTO.firstName)
                .lastName(registerDTO.lastName)
                .email(registerDTO.email)
                .password(encoder.encode(registerDTO.password))
                .createdAt(LocalDateTime.now())
                .role(registerDTO.role)
                .isActive(true) //Set to false if we are going to send activation account emails
                .build();
        userRepository.save(newUser);
        User savedUser = userRepository.save(newUser);
        // Create activation token
        LocalDateTime expiresIn = LocalDateTime.now().plusDays(1);
        UserVerificationToken savedToken = this.createVerificationToken(expiresIn, savedUser);
        // Send email with verificationToken
        return true;
    }

    public boolean activateAccount(String token) {
        // Validate verification token
        Optional<UserVerificationToken> found = userVerificationTokenRepository.findByToken(token);
        if (found.isPresent()) {
            User user = found.get().getUser();
            if (user.isActive()) {
                throw new IllegalArgumentException("Account already active");
            }
            UserVerificationToken verificationToken = found.get();
            // Verify if token is still valid
            LocalDateTime expiresIn = verificationToken.getExpiryDate();
            LocalDateTime now = LocalDateTime.now();
            if (expiresIn.isAfter(now) && verificationToken.getToken().equals(token)) {
                // Update user account to active
                user.setActive(true);
                userRepository.save(user);
                this.updateUserVerificationTokenExpirationDate(verificationToken);
                return true;
            } else {
                throw new IllegalArgumentException("Activation link already used");
            }
        }
        return false;
    }

    public boolean forgotPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User found = user.get();
            UserVerificationToken token;
            if (userVerificationTokenRepository.findByUser(found).isPresent()) {
                token = userVerificationTokenRepository.findByUser(found).get();
                // Set validation token date
                LocalDateTime expiresIn = LocalDateTime.now().plusMinutes(30);
                token.setExpiryDate(expiresIn);
                userVerificationTokenRepository.save(token);
                return true;
            }
        } else {
            throw new IllegalArgumentException("Email not found");
        }
        return false;
    }

    public boolean resetPassword(String verifyToken, ResetPasswordDTO resetPasswordDTO) {
        if (!resetPasswordDTO.password.equals(resetPasswordDTO.passwordConfirmation)) {
            throw new IllegalArgumentException("Password and confirmation do not match.");
        }
        if (!isValidPassword(resetPasswordDTO.password)) {
            throw new IllegalArgumentException("Error: invalid password!");
        }
        if (verifyToken != null) {
            // Forgot password
            Optional<UserVerificationToken> verificationToken = userVerificationTokenRepository.findByToken(verifyToken);
            if (verificationToken.isPresent()
                    && verificationToken.get().getToken().equals(verifyToken)
                    && verificationToken.get().getExpiryDate().isAfter(LocalDateTime.now())
            ) {
                User foundUser = verificationToken.get().getUser();
                return this.updateUserPassword(foundUser, resetPasswordDTO.password);
            } else {
                throw new IllegalArgumentException("Error: invalid token!");
            }
        }
        return false;
    }

    private boolean updateUserPassword(User user, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }

    private UserVerificationToken createVerificationToken(LocalDateTime expiration, User user) {
        String token = RandomStringUtils.randomAlphanumeric(32);
        LocalDateTime now = LocalDateTime.now();
        UserVerificationToken verificationToken = UserVerificationToken.builder()
                .token(token)
                .user(user)
                .createdDate(now)
                .expiryDate(expiration)
                .build();
        return userVerificationTokenRepository.save(verificationToken);
    }

    private void updateUserVerificationTokenExpirationDate(UserVerificationToken verificationToken) {
        // Update token expiration time to now
        verificationToken.setExpiryDate(LocalDateTime.now());
        userVerificationTokenRepository.save(verificationToken);
    }

    public boolean deleteAccount(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        long currentTimeMillis = System.currentTimeMillis();
        String newEmail = "deleted" + currentTimeMillis + "@deleted.deleted";
        if (foundUser.isPresent()) {
            User updatedUser = foundUser.get();
            updatedUser.setDeleted(true);
            updatedUser.setActive(false);
            updatedUser.setFirstName("deleted");
            updatedUser.setLastName("deleted");
            updatedUser.setEmail(newEmail);
            updatedUser.setPassword(String.valueOf(currentTimeMillis));
            updatedUser.setRole(ERole.DELETED);
            userRepository.save(updatedUser);
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password) {
        // At least one upper case, one lower case, one digit, one special character, min. length of 8
        String regEx = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(regEx);
    }

    private boolean isValidPhoneNumber(String number) {
        // string has only numbers, no length limit
        return number.matches("[0-9]+");
    }

    public Cookie createCookie(String jwtToken) {
        int jwtExpirationAge = 60 * jwtExpirationMin * 24; // 1 day
        Cookie cookie = new Cookie(jwtCookie, jwtToken);
        cookie.setPath("/"); // Global
        cookie.setMaxAge(jwtExpirationAge);
        return cookie;
    }

    public Cookie logout() {
        Cookie cookie = new Cookie(jwtCookie, "");
        cookie.setPath("/"); // Global
        cookie.setMaxAge(0);
        return cookie;
    }

}
