package backend.isac.service;

import backend.isac.dto.ProcessDTO;
import backend.isac.model.Process;
import backend.isac.repository.ProcessRepository;
import backend.isac.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessServiceImpl(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }


    @Override
    public List<ProcessDTO> getAllProcesses() {
        return processRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProcessDTO getProcessById(Long id) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + id));
        return toDTO(process);
    }

    @Override
    public ProcessDTO createProcess(ProcessDTO processDTO) {
        Process process = toEntity(processDTO);
        Process savedProcess = processRepository.save(process);
        return toDTO(savedProcess);
    }

    @Override
    public ProcessDTO updateProcess(Long id, ProcessDTO processDTO) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + id));
        process.setProcessName(processDTO.getProcessName());
        Process updatedProcess = processRepository.save(process);
        return toDTO(updatedProcess);
    }

    @Override
    public void deleteProcess(Long id) {
        if (!processRepository.existsById(id)) {
            throw new ResourceNotFoundException("Process not found with id " + id);
        }
        processRepository.deleteById(id);
    }

    private Process toEntity(ProcessDTO dto) {
        Process process = new Process();
        process.setId(dto.getId());
        process.setProcessName(dto.getProcessName());
        return process;
    }

    private ProcessDTO toDTO(Process process) {
        ProcessDTO dto = new ProcessDTO();
        dto.setId(process.getId());
        dto.setProcessName(process.getProcessName());
        return dto;
    }
}