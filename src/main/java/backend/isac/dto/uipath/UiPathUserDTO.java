package backend.isac.dto.uipath;

import lombok.Data;

@Data
public class UiPathUserDTO {
    private Long id;
    private String username;
    private String surname;
    private String name;
    private String emailAddress;
    private Boolean isActive;
    private Long tenantId;
}