package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathOrchestratorDTO;

import java.util.List;

public interface UiPathOrchestratorService {
    List<UiPathOrchestratorDTO> findAll();

    UiPathOrchestratorDTO findById(Long id);

    UiPathOrchestratorDTO save(UiPathOrchestratorDTO orchestratorDTO);

    void delete(Long id);
}
