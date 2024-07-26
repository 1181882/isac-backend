package backend.isac.service;

import backend.isac.dto.ProcessDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.ProcessMapper;
import backend.isac.model.Process;
import backend.isac.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;
    private final ProcessMapper processMapper;

    @Autowired
    public ProcessServiceImpl(ProcessRepository processRepository, ProcessMapper processMapper) {
        this.processRepository = processRepository;
        this.processMapper = processMapper;
    }

    @Override
    public List<ProcessDTO> getAllProcesses() {
        return processRepository.findAll().stream().map(processMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProcessDTO getProcessById(Long id) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + id));
        return processMapper.toDTO(process);
    }

    @Override
    public ProcessDTO createProcess(ProcessDTO processDTO) {
        Process process = processMapper.toEntity(processDTO);
        Process savedProcess = processRepository.save(process);
        return processMapper.toDTO(savedProcess);
    }

    @Override
    public ProcessDTO updateProcess(Long id, ProcessDTO processDTO) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + id));
        process.setProcessName(processDTO.getProcessName());
        Process updatedProcess = processRepository.save(process);
        return processMapper.toDTO(updatedProcess);
    }

    @Override
    public void deleteProcess(Long id) {
        if (!processRepository.existsById(id)) {
            throw new ResourceNotFoundException("Process not found with id " + id);
        }
        processRepository.deleteById(id);
    }
}
