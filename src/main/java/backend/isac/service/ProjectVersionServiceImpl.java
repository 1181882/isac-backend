package backend.isac.service;

import backend.isac.dto.ProjectVersionDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.ProjectVersionMapper;
import backend.isac.model.Dashboard;
import backend.isac.model.Process;
import backend.isac.model.ProjectVersion;
import backend.isac.repository.DashboardRepository;
import backend.isac.repository.ProcessRepository;
import backend.isac.repository.ProjectVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectVersionServiceImpl implements ProjectVersionService {

    private final ProjectVersionRepository projectVersionRepository;
    private final ProcessRepository processRepository;
    private final DashboardRepository dashboardRepository;
    private final ProjectVersionMapper projectVersionMapper;

    @Autowired
    public ProjectVersionServiceImpl(ProjectVersionRepository projectVersionRepository, ProcessRepository processRepository, DashboardRepository dashboardRepository, ProjectVersionMapper projectVersionMapper) {
        this.projectVersionRepository = projectVersionRepository;
        this.processRepository = processRepository;
        this.dashboardRepository = dashboardRepository;
        this.projectVersionMapper = projectVersionMapper;
    }

    @Override
    public ProjectVersionDTO createProjectVersion(ProjectVersionDTO projectVersionDTO) {
        ProjectVersion projectVersion = projectVersionMapper.toEntity(projectVersionDTO);
        projectVersion.setCreatedDate(LocalDateTime.now());

        ProjectVersion savedVersion = projectVersionRepository.save(projectVersion);
        return projectVersionMapper.toDTO(savedVersion);
    }

    @Override
    public ProjectVersionDTO updateProjectVersion(Long id, ProjectVersionDTO projectVersionDTO) {
        ProjectVersion projectVersion = projectVersionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with id " + id));

        projectVersion.setDeployDateQa(projectVersionDTO.getDeployDateQa());
        projectVersion.setDeployDateProd(projectVersionDTO.getDeployDateProd());
        projectVersion.setLifecycle(projectVersionDTO.getLifecycle());
        projectVersion.setStatus(projectVersionDTO.getStatus());
        projectVersion.setVersion(projectVersionDTO.getVersion());

        projectVersion.setProcesses(projectVersionDTO.getProcessIds().stream()
                .map(processId -> processRepository.findById(processId)
                        .orElseThrow(() -> new ResourceNotFoundException("Process not found with id " + processId)))
                .collect(Collectors.toList()));

        projectVersion.setDashboards(projectVersionDTO.getDashboardIds().stream()
                .map(dashboardId -> dashboardRepository.findById(dashboardId)
                        .orElseThrow(() -> new ResourceNotFoundException("Dashboard not found with id " + dashboardId)))
                .collect(Collectors.toList()));

        ProjectVersion updatedProjectVersion = projectVersionRepository.save(projectVersion);
        return projectVersionMapper.toDTO(updatedProjectVersion);
    }

    @Override
    public void deleteProjectVersion(Long id) {
        ProjectVersion projectVersion = projectVersionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with id " + id));
        projectVersionRepository.delete(projectVersion);
    }

    @Override
    public ProjectVersionDTO getProjectVersionById(Long id) {
        ProjectVersion projectVersion = projectVersionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with id " + id));
        return projectVersionMapper.toDTO(projectVersion);
    }

    @Override
    public List<ProjectVersionDTO> getAllProjectVersions() {
        return projectVersionRepository.findAll().stream()
                .map(projectVersionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
