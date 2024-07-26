package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathProcessDTO;
import backend.isac.mapper.uipath.UiPathProcessMapper;
import backend.isac.model.uipath.UiPathProcess;
import backend.isac.repository.uipath.UiPathProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathProcessServiceImpl implements UiPathProcessService {

    private final UiPathProcessRepository processRepository;
    private final UiPathProcessMapper entityMapper;

    @Autowired
    public UiPathProcessServiceImpl(UiPathProcessRepository processRepository, UiPathProcessMapper entityMapper) {
        this.processRepository = processRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathProcessDTO> findAll() {
        return processRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathProcessDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathProcessDTO findById(Long id) {
        UiPathProcess process = processRepository.findById(id).orElse(null);
        return entityMapper.toUiPathProcessDTO(process);
    }

    @Override
    public UiPathProcessDTO save(UiPathProcessDTO processDTO) {
        UiPathProcess process = entityMapper.toUiPathProcess(processDTO);
        UiPathProcess savedProcess = processRepository.save(process);
        return entityMapper.toUiPathProcessDTO(savedProcess);
    }

    @Override
    public void delete(Long id) {
        processRepository.deleteById(id);
    }
}