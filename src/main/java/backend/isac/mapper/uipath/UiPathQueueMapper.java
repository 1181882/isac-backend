package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathQueueDTO;
import backend.isac.model.uipath.UiPathQueue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathQueueMapper {
    @Mapping(source = "folder.id", target = "folderId")
    UiPathQueueDTO toUiPathQueueDTO(UiPathQueue queue);

    @Mapping(source = "folderId", target = "folder.id")
    UiPathQueue toUiPathQueue(UiPathQueueDTO queueDTO);
}