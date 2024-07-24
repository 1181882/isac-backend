package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathUserDTO;

import java.util.List;

public interface UiPathUserService {
    List<UiPathUserDTO> findAll();

    UiPathUserDTO findById(Long id);

    UiPathUserDTO save(UiPathUserDTO userDTO);

    void delete(Long id);
}