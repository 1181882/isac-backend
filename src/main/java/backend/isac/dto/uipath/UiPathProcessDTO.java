package backend.isac.dto.uipath;

import lombok.Data;

import java.util.List;
@Data
public class UiPathProcessDTO {
    private Long id;
    private String name;
    private String description;
    private String key;
    private String version;
    private Long folderId;
    private List<UiPathTriggerDTO> triggers;
}
