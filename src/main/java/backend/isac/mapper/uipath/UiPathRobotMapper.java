package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathRobotDTO;
import backend.isac.model.uipath.UiPathRobot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathRobotMapper {
    @Mapping(source = "machine.id", target = "machineId")
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathRobotDTO toUiPathRobotDTO(UiPathRobot robot);

    @Mapping(source = "machineId", target = "machine.id")
    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathRobot toUiPathRobot(UiPathRobotDTO robotDTO);
}