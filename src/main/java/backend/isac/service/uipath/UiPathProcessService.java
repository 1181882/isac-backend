package backend.isac.service.uipath;

import backend.isac.dto.uipath.*;

import java.util.List;

public interface UiPathProcessService {
    List<UiPathProcessDTO> findAll();

    UiPathProcessDTO findById(Long id);

    UiPathProcessDTO save(UiPathProcessDTO processDTO);

    void delete(Long id);
}