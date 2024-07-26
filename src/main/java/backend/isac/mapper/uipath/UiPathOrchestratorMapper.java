package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathOrchestratorDTO;
import backend.isac.model.uipath.UiPathOrchestrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathOrchestratorMapper {
    @Mapping(source = "tenants", target = "tenants")
    UiPathOrchestratorDTO toUiPathOrchestratorDTO(UiPathOrchestrator orchestrator);

    @Mapping(target = "tenants", ignore = true)
    UiPathOrchestrator toUiPathOrchestrator(UiPathOrchestratorDTO orchestratorDTO);
}