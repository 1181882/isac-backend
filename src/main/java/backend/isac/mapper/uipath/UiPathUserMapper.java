package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathUserDTO;
import backend.isac.model.uipath.UiPathUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathUserMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathUserDTO toUiPathUserDTO(UiPathUser user);

    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathUser toUiPathUser(UiPathUserDTO userDTO);
}