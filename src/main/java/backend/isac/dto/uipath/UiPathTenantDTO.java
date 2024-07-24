package backend.isac.dto.uipath;

import lombok.Data;

import java.util.List;

@Data
public class UiPathTenantDTO {
    private Long id;
    private String name;
    private Long orchestratorId;
    private List<UiPathFolderDTO> folders;
    private List<UiPathLicenseDTO> licenses;
    private List<UiPathUserDTO> users;
    private List<UiPathMachineDTO> machines;
    private List<UiPathRobotDTO> robots;
}
