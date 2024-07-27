package backend.isac.dto.uipath;

import backend.isac.model.Version;
import backend.isac.model.enums.EUipathLicenseType;
import lombok.Data;

@Data
public class UiPathRobotDTO {
    private Long id;
    private String name;
    private String type;
    private Version version;
    private Long machineId;
    private Long tenantId;
    private Long environmentId;
    private EUipathLicenseType licenseType;
}