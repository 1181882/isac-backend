package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathTenantDTO;
import backend.isac.model.uipath.UiPathTenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathTenantMapper {
    @Mapping(source = "orchestrator.id", target = "orchestratorId")
    UiPathTenantDTO toUiPathTenantDTO(UiPathTenant tenant);

    @Mapping(source = "orchestratorId", target = "orchestrator.id")
    UiPathTenant toUiPathTenant(UiPathTenantDTO tenantDTO);
}