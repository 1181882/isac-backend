package backend.isac.service.uipath;

import backend.isac.dto.uipath.*;

import java.util.List;

public interface UiPathFolderService {
    List<UiPathFolderDTO> findAll();

    UiPathFolderDTO findById(Long id);

    UiPathFolderDTO save(UiPathFolderDTO folderDTO);

    void delete(Long id);
}