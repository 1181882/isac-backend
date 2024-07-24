package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathMachineDTO;

import java.util.List;

public interface UiPathMachineService {
    List<UiPathMachineDTO> findAll();

    UiPathMachineDTO findById(Long id);

    UiPathMachineDTO save(UiPathMachineDTO machineDTO);

    void delete(Long id);
}