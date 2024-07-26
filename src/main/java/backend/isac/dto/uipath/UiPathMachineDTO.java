package backend.isac.dto.uipath;

import backend.isac.model.Version;
import lombok.Data;

import java.util.List;

@Data
public class UiPathMachineDTO {
    private Long id;
    private String name;
    private String type;
    private Version version;
    private Long tenantId;
    private List<UiPathRobotDTO> robots;
}