package backend.isac.dto.uipath;

import lombok.Data;

@Data
public class UiPathTriggerDTO {
    private Long id;
    private String name;
    private Boolean enabled;
    private String runtimeType;
    private String startProcessCronExpression;
    private Long processId;
}
