package backend.isac.dto.uipath;

import lombok.Data;

import java.util.List;

@Data
public class UiPathMachineDTO {
    private Long id;
    private String name;
    private String type;
    private String version;
    private Long tenantId;
    private List<UiPathRobotDTO> robots;
}