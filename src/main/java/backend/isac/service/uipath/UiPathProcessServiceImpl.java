package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathProcessDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.uipath.UiPathProcessMapper;
import backend.isac.model.ProjectVersion;
import backend.isac.model.uipath.UiPathProcess;
import backend.isac.repository.uipath.UiPathProcessRepository;
import backend.isac.repository.ProjectVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathProcessServiceImpl implements UiPathProcessService {

    private final UiPathProcessRepository processRepository;
    private final ProjectVersionRepository projectVersionRepository;
    private final UiPathProcessMapper entityMapper;

    @Autowired
    public UiPathProcessServiceImpl(UiPathProcessRepository processRepository, ProjectVersionRepository projectVersionRepository, UiPathProcessMapper entityMapper) {
        this.processRepository = processRepository;
        this.projectVersionRepository = projectVersionRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathProcessDTO> findAll() {
        return processRepository.findAll()
                .stream()
                .map(process -> {
                    UiPathProcessDTO dto = entityMapper.toUiPathProcessDTO(process);
                    dto.setProjectVersionIds(process.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UiPathProcessDTO findById(Long id) {
        return processRepository.findById(id).map(process -> {
            UiPathProcessDTO dto = entityMapper.toUiPathProcessDTO(process);
            dto.setProjectVersionIds(process.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
            return dto;
        }).orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + id));
    }

    @Override
    public UiPathProcessDTO save(UiPathProcessDTO processDTO) {
        UiPathProcess process = entityMapper.toUiPathProcess(processDTO);
        if (processDTO.getProjectVersionIds() != null) {
            List<ProjectVersion> projectVersions = projectVersionRepository.findAllById(processDTO.getProjectVersionIds());
            process.setProjectVersions(projectVersions);
        }
        UiPathProcess savedProcess = processRepository.save(process);
        return entityMapper.toUiPathProcessDTO(savedProcess);
    }

    @Override
    public void delete(Long id) {
        processRepository.deleteById(id);
    }
}
