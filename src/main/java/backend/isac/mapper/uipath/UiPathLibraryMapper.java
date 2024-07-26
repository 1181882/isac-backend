package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathLibraryDTO;
import backend.isac.model.uipath.UiPathLibrary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathLibraryMapper {
    @Mapping(source = "folder.id", target = "folderId")
    UiPathLibraryDTO toUiPathLibraryDTO(UiPathLibrary library);

    @Mapping(source = "folderId", target = "folder.id")
    UiPathLibrary toUiPathLibrary(UiPathLibraryDTO libraryDTO);
}