package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathEnvironmentDTO;
import backend.isac.mapper.EntityMapper;
import backend.isac.model.uipath.UiPathEnvironment;
import backend.isac.repository.uipath.UiPathEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathEnvironmentServiceImpl implements UiPathEnvironmentService {

    private final UiPathEnvironmentRepository environmentRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UiPathEnvironmentServiceImpl(UiPathEnvironmentRepository environmentRepository, EntityMapper entityMapper) {
        this.environmentRepository = environmentRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathEnvironmentDTO> findAll() {
        return environmentRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathEnvironmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathEnvironmentDTO findById(Long id) {
        UiPathEnvironment environment = environmentRepository.findById(id).orElse(null);
        return entityMapper.toUiPathEnvironmentDTO(environment);
    }

    @Override
    public UiPathEnvironmentDTO save(UiPathEnvironmentDTO environmentDTO) {
        UiPathEnvironment environment = entityMapper.toUiPathEnvironment(environmentDTO);
        UiPathEnvironment savedEnvironment = environmentRepository.save(environment);
        return entityMapper.toUiPathEnvironmentDTO(savedEnvironment);
    }

    @Override
    public void delete(Long id) {
        environmentRepository.deleteById(id);
    }
}