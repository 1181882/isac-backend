package backend.isac.dto;

import backend.isac.model.enums.EApplicationType;
import backend.isac.model.enums.EAutomationType;
import lombok.Data;

import java.util.List;

@Data
public class AutomatedApplicationDTO {

    private Long id;
    private String name;
    private EApplicationType applicationType;
    private EAutomationType automationType;
    private List<Long> uipathProcessIds;
}
