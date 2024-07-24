package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathQueueDTO;

import java.util.List;

public interface UiPathQueueService {
    List<UiPathQueueDTO> findAll();

    UiPathQueueDTO findById(Long id);

    UiPathQueueDTO save(UiPathQueueDTO queueDTO);

    void delete(Long id);
}