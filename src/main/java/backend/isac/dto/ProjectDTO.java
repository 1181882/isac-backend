package backend.isac.dto;

import backend.isac.model.enums.EAsset;
import backend.isac.model.enums.EImpact;
import backend.isac.model.enums.ESupportTeam;
import backend.isac.model.enums.EStatus;
import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String projectCode;
    private String name;
    private Long iuaCodeId;
    private EImpact impact;
    private ESupportTeam supportTeam;
    private EAsset asset;
    private EStatus status;
    private Long departmentId;
}
