package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathEnvironmentDTO;
import backend.isac.model.uipath.UiPathEnvironment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathEnvironmentMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathEnvironmentDTO toUiPathEnvironmentDTO(UiPathEnvironment environment);

    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathEnvironment toUiPathEnvironment(UiPathEnvironmentDTO environmentDTO);
}