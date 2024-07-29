package backend.isac.dto.uipath;

import backend.isac.model.Version;
import lombok.Data;

import java.util.List;

@Data
public class UiPathProcessDTO {
    private Long id;
    private String name;
    private String description;
    private String key;
    private Version version;
    private Long folderId;
    private List<UiPathTriggerDTO> triggers;
    private List<Long> projectVersionIds;
}
