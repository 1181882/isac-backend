package backend.isac.dto.uipath;

import lombok.Data;

@Data
public class UiPathRobotDTO {
    private Long id;
    private String name;
    private String type;
    private String version;
    private Long machineId;
    private Long tenantId;
    private Long environmentId;
}