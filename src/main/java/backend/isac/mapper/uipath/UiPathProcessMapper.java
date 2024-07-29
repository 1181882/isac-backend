package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathProcessDTO;
import backend.isac.model.uipath.UiPathProcess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathProcessMapper {
    @Mapping(source = "folder.id", target = "folderId")
    UiPathProcessDTO toUiPathProcessDTO(UiPathProcess process);

    @Mapping(source = "folderId", target = "folder.id")
    @Mapping(target = "projectVersions", ignore = true)
    UiPathProcess toUiPathProcess(UiPathProcessDTO processDTO);
}
