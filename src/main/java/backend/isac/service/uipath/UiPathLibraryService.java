package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathLibraryDTO;

import java.util.List;

public interface UiPathLibraryService {
    List<UiPathLibraryDTO> findAll();

    UiPathLibraryDTO findById(Long id);

    UiPathLibraryDTO save(UiPathLibraryDTO libraryDTO);

    void delete(Long id);
}
