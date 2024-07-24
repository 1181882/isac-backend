package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathTriggerDTO;

import java.util.List;

public interface UiPathTriggerService {
    List<UiPathTriggerDTO> findAll();

    UiPathTriggerDTO findById(Long id);

    UiPathTriggerDTO save(UiPathTriggerDTO triggerDTO);

    void delete(Long id);
}
