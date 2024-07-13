package backend.isac.security.dto;

import backend.isac.model.enums.ERole;

public class AuthRegisterDTO {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public ERole role;

}
