package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathProcessDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.uipath.UiPathProcessMapper;
import backend.isac.model.ProjectVersion;
import backend.isac.model.uipath.UiPathProcess;
import backend.isac.model.AutomatedApplication;
import backend.isac.repository.uipath.UiPathProcessRepository;
import backend.isac.repository.ProjectVersionRepository;
import backend.isac.repository.AutomatedApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathProcessServiceImpl implements UiPathProcessService {

    private final UiPathProcessRepository processRepository;
    private final ProjectVersionRepository projectVersionRepository;
    private final AutomatedApplicationRepository automatedApplicationRepository;
    private final UiPathProcessMapper entityMapper;

    @Autowired
    public UiPathProcessServiceImpl(UiPathProcessRepository processRepository, ProjectVersionRepository projectVersionRepository, AutomatedApplicationRepository automatedApplicationRepository, UiPathProcessMapper entityMapper) {
        this.processRepository = processRepository;
        this.projectVersionRepository = projectVersionRepository;
        this.automatedApplicationRepository = automatedApplicationRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathProcessDTO> findAll() {
        return processRepository.findAll()
                .stream()
                .map(this::mapToUiPathProcessDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathProcessDTO findById(Long id) {
        return processRepository.findById(id)
                .map(this::mapToUiPathProcessDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + id));
    }

    @Override
    public UiPathProcessDTO save(UiPathProcessDTO processDTO) {
        UiPathProcess process = entityMapper.toUiPathProcess(processDTO);
        if (processDTO.getProjectVersionIds() != null) {
            List<ProjectVersion> projectVersions = projectVersionRepository.findAllById(processDTO.getProjectVersionIds());
            process.setProjectVersions(projectVersions);
        }
        if (processDTO.getAutomatedApplicationIds() != null) {
            List<AutomatedApplication> automatedApplications = automatedApplicationRepository.findAllById(processDTO.getAutomatedApplicationIds());
            process.setAutomatedApplications(automatedApplications);
        }
        UiPathProcess savedProcess = processRepository.save(process);
        return mapToUiPathProcessDTO(savedProcess);
    }

    @Override
    public void delete(Long id) {
        processRepository.deleteById(id);
    }

    private UiPathProcessDTO mapToUiPathProcessDTO(UiPathProcess process) {
        UiPathProcessDTO dto = entityMapper.toUiPathProcessDTO(process);
        dto.setProjectVersionIds(process.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
        dto.setAutomatedApplicationIds(process.getAutomatedApplications().stream().map(AutomatedApplication::getId).collect(Collectors.toList()));
        return dto;
    }
}
