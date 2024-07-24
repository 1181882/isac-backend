package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathRobotDTO;
import backend.isac.mapper.EntityMapper;
import backend.isac.model.uipath.UiPathRobot;
import backend.isac.repository.uipath.UiPathRobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathRobotServiceImpl implements UiPathRobotService {

    private final UiPathRobotRepository robotRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UiPathRobotServiceImpl(UiPathRobotRepository robotRepository, EntityMapper entityMapper) {
        this.robotRepository = robotRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathRobotDTO> findAll() {
        return robotRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathRobotDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathRobotDTO findById(Long id) {
        UiPathRobot robot = robotRepository.findById(id).orElse(null);
        return entityMapper.toUiPathRobotDTO(robot);
    }

    @Override
    public UiPathRobotDTO save(UiPathRobotDTO robotDTO) {
        UiPathRobot robot = entityMapper.toUiPathRobot(robotDTO);
        UiPathRobot savedRobot = robotRepository.save(robot);
        return entityMapper.toUiPathRobotDTO(savedRobot);
    }

    @Override
    public void delete(Long id) {
        robotRepository.deleteById(id);
    }
}
