package backend.isac.dto.uipath;

import lombok.Data;

import java.util.List;

@Data
public class UiPathEnvironmentDTO {
    private Long id;
    private String name;
    private Long tenantId;
    private List<UiPathRobotDTO> robots;
}