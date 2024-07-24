package backend.isac.dto.uipath;

import lombok.Data;

import java.util.List;
@Data
public class UiPathFolderDTO {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private Boolean isActive;
    private Long tenantId;
    private List<UiPathProcessDTO> processes;
    private List<UiPathQueueDTO> queues;
    private List<UiPathLibraryDTO> libraries;
}
