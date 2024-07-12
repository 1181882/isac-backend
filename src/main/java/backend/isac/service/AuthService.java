package backend.isac.service;

import backend.isac.security.dto.AuthLoginDTO;
import backend.isac.security.dto.AuthRegisterDTO;
import backend.isac.security.dto.AuthResponseDTO;
import backend.isac.security.dto.ResetPasswordDTO;
import jakarta.servlet.http.Cookie;

public interface AuthService {
    AuthResponseDTO login(AuthLoginDTO loginRequest);
    boolean registerUser(AuthRegisterDTO registerDTO);
    boolean activateAccount(String token);
    boolean deleteAccount(String email);
    Cookie createCookie(String jwtToken);
    Cookie logout();
    boolean forgotPassword(String email);
    boolean resetPassword(String verifyToken, ResetPasswordDTO resetPasswordDTO);
}
