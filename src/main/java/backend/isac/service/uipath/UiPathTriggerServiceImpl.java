package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathTriggerDTO;
import backend.isac.mapper.EntityMapper;
import backend.isac.model.uipath.UiPathTrigger;
import backend.isac.repository.uipath.UiPathTriggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathTriggerServiceImpl implements UiPathTriggerService {

    private final UiPathTriggerRepository triggerRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UiPathTriggerServiceImpl(UiPathTriggerRepository triggerRepository, EntityMapper entityMapper) {
        this.triggerRepository = triggerRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathTriggerDTO> findAll() {
        return triggerRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathTriggerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathTriggerDTO findById(Long id) {
        UiPathTrigger trigger = triggerRepository.findById(id).orElse(null);
        return entityMapper.toUiPathTriggerDTO(trigger);
    }

    @Override
    public UiPathTriggerDTO save(UiPathTriggerDTO triggerDTO) {
        UiPathTrigger trigger = entityMapper.toUiPathTrigger(triggerDTO);
        UiPathTrigger savedTrigger = triggerRepository.save(trigger);
        return entityMapper.toUiPathTriggerDTO(savedTrigger);
    }

    @Override
    public void delete(Long id) {
        triggerRepository.deleteById(id);
    }
}
