package backend.isac.dto.uipath;

import lombok.Data;

@Data
public class UiPathLicenseDTO {
    private Long id;
    private String licenseCode;
    private String licenseType;
    private Long tenantId;
}
