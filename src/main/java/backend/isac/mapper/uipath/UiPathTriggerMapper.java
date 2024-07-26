package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathTriggerDTO;
import backend.isac.model.uipath.UiPathTrigger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathTriggerMapper {
    @Mapping(source = "process.id", target = "processId")
    UiPathTriggerDTO toUiPathTriggerDTO(UiPathTrigger trigger);

    @Mapping(source = "processId", target = "process.id")
    UiPathTrigger toUiPathTrigger(UiPathTriggerDTO triggerDTO);
}