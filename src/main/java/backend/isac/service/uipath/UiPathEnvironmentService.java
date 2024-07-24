package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathEnvironmentDTO;

import java.util.List;

public interface UiPathEnvironmentService {
    List<UiPathEnvironmentDTO> findAll();

    UiPathEnvironmentDTO findById(Long id);

    UiPathEnvironmentDTO save(UiPathEnvironmentDTO environmentDTO);

    void delete(Long id);
}
