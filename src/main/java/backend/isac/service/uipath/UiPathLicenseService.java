package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathLicenseDTO;

import java.util.List;

public interface UiPathLicenseService {
    List<UiPathLicenseDTO> findAll();

    UiPathLicenseDTO findById(Long id);

    UiPathLicenseDTO save(UiPathLicenseDTO licenseDTO);

    void delete(Long id);
}