package backend.isac.mapper.uipath;

import backend.isac.dto.uipath.UiPathLicenseDTO;
import backend.isac.model.uipath.UiPathLicense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UiPathLicenseMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathLicenseDTO toUiPathLicenseDTO(UiPathLicense license);

    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathLicense toUiPathLicense(UiPathLicenseDTO licenseDTO);
}