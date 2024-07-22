package backend.isac.service;

import backend.isac.dto.ProcessDTO;

import java.util.List;

public interface ProcessService {
    List<ProcessDTO> getAllProcesses();

    ProcessDTO getProcessById(Long id);

    ProcessDTO createProcess(ProcessDTO processDTO);

    ProcessDTO updateProcess(Long id, ProcessDTO processDTO);

    void deleteProcess(Long id);
}