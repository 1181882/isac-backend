package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathFolderDTO;
import backend.isac.model.uipath.UiPathFolder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathFolderMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathFolderDTO toUiPathFolderDTO(UiPathFolder folder);

    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathFolder toUiPathFolder(UiPathFolderDTO folderDTO);
}