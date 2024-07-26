package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathMachineDTO;
import backend.isac.mapper.uipath.UiPathMachineMapper;
import backend.isac.model.uipath.UiPathMachine;
import backend.isac.repository.uipath.UiPathMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathMachineServiceImpl implements UiPathMachineService {

    private final UiPathMachineRepository machineRepository;
    private final UiPathMachineMapper entityMapper;

    @Autowired
    public UiPathMachineServiceImpl(UiPathMachineRepository machineRepository, UiPathMachineMapper entityMapper) {
        this.machineRepository = machineRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathMachineDTO> findAll() {
        return machineRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathMachineDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathMachineDTO findById(Long id) {
        UiPathMachine machine = machineRepository.findById(id).orElse(null);
        return entityMapper.toUiPathMachineDTO(machine);
    }

    @Override
    public UiPathMachineDTO save(UiPathMachineDTO machineDTO) {
        UiPathMachine machine = entityMapper.toUiPathMachine(machineDTO);
        UiPathMachine savedMachine = machineRepository.save(machine);
        return entityMapper.toUiPathMachineDTO(savedMachine);
    }

    @Override
    public void delete(Long id) {
        machineRepository.deleteById(id);
    }
}
