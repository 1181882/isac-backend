package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathTenantDTO;

import java.util.List;

public interface UiPathTenantService {

    List<UiPathTenantDTO> findAll();

    UiPathTenantDTO findById(Long id);

    UiPathTenantDTO save(UiPathTenantDTO tenantDTO);

    void delete(Long id);
}
