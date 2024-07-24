package backend.isac.dto.uipath;

import lombok.Data;

@Data
public class UiPathQueueDTO {
    private Long id;
    private String name;
    private String description;
    private Long folderId;
}