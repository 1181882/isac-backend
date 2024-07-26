package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathQueueDTO;
import backend.isac.mapper.uipath.UiPathQueueMapper;
import backend.isac.model.uipath.UiPathQueue;
import backend.isac.repository.uipath.UiPathQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathQueueServiceImpl implements UiPathQueueService {

    private final UiPathQueueRepository queueRepository;
    private final UiPathQueueMapper entityMapper;

    @Autowired
    public UiPathQueueServiceImpl(UiPathQueueRepository queueRepository, UiPathQueueMapper entityMapper) {
        this.queueRepository = queueRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathQueueDTO> findAll() {
        return queueRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathQueueDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathQueueDTO findById(Long id) {
        UiPathQueue queue = queueRepository.findById(id).orElse(null);
        return entityMapper.toUiPathQueueDTO(queue);
    }

    @Override
    public UiPathQueueDTO save(UiPathQueueDTO queueDTO) {
        UiPathQueue queue = entityMapper.toUiPathQueue(queueDTO);
        UiPathQueue savedQueue = queueRepository.save(queue);
        return entityMapper.toUiPathQueueDTO(savedQueue);
    }

    @Override
    public void delete(Long id) {
        queueRepository.deleteById(id);
    }
}
