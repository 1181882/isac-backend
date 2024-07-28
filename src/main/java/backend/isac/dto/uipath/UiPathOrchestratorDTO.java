package backend.isac.dto.uipath;

import backend.isac.model.enums.EEnvironmentType;
import lombok.Data;

import java.util.List;

@Data
public class UiPathOrchestratorDTO {
    private Long id;
    private String url;
    private String authToken;
    private EEnvironmentType environmentType;
    private List<UiPathTenantDTO> tenants;
}
