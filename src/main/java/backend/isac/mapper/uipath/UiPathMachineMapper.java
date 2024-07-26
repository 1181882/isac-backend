package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathMachineDTO;
import backend.isac.model.uipath.UiPathMachine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathMachineMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathMachineDTO toUiPathMachineDTO(UiPathMachine machine);

    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathMachine toUiPathMachine(UiPathMachineDTO machineDTO);
}