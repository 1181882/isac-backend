package backend.isac.service;

import backend.isac.dto.AuthLoginDTO;
import backend.isac.dto.AuthRegisterDTO;
import backend.isac.dto.AuthResponseDTO;
import backend.isac.dto.ResetPasswordDTO;
import jakarta.servlet.http.Cookie;

public interface AuthService {
    AuthResponseDTO login(AuthLoginDTO loginRequest);
    boolean registerUser(AuthRegisterDTO registerDTO);
    boolean activateAccount(String token);
    boolean deleteAccount(String email);
    boolean deleteAccountAsAdmin(String email, String currentUser);
    Cookie createCookie(String jwtToken);
    Cookie logout();
    boolean forgotPassword(String email);
    boolean resetPassword(String verifyToken, ResetPasswordDTO resetPasswordDTO);
}
