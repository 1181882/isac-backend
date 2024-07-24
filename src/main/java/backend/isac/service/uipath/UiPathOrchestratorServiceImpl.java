package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathOrchestratorDTO;
import backend.isac.mapper.EntityMapper;
import backend.isac.model.uipath.UiPathOrchestrator;
import backend.isac.repository.uipath.UiPathOrchestratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathOrchestratorServiceImpl implements UiPathOrchestratorService {

    private final UiPathOrchestratorRepository orchestratorRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UiPathOrchestratorServiceImpl(UiPathOrchestratorRepository orchestratorRepository, EntityMapper entityMapper) {
        this.orchestratorRepository = orchestratorRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathOrchestratorDTO> findAll() {
        return orchestratorRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathOrchestratorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathOrchestratorDTO findById(Long id) {
        UiPathOrchestrator orchestrator = orchestratorRepository.findById(id).orElse(null);
        return entityMapper.toUiPathOrchestratorDTO(orchestrator);
    }

    @Override
    public UiPathOrchestratorDTO save(UiPathOrchestratorDTO orchestratorDTO) {
        UiPathOrchestrator orchestrator = entityMapper.toUiPathOrchestrator(orchestratorDTO);
        UiPathOrchestrator savedOrchestrator = orchestratorRepository.save(orchestrator);
        return entityMapper.toUiPathOrchestratorDTO(savedOrchestrator);
    }

    @Override
    public void delete(Long id) {
        orchestratorRepository.deleteById(id);
    }
}
