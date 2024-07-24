package backend.isac.dto.uipath;

import lombok.Data;

import java.util.List;

@Data
public class UiPathOrchestratorDTO {
    private Long id;
    private String url;
    private String authToken;
    private List<UiPathTenantDTO> tenants;
}
