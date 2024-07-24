package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathRobotDTO;

import java.util.List;

public interface UiPathRobotService {
    List<UiPathRobotDTO> findAll();

    UiPathRobotDTO findById(Long id);

    UiPathRobotDTO save(UiPathRobotDTO robotDTO);

    void delete(Long id);
}
