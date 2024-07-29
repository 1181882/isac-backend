package backend.isac.service;

import backend.isac.model.uipath.UiPathProcess;
import backend.isac.dto.ProjectVersionDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.ProjectVersionMapper;
import backend.isac.model.*;
import backend.isac.repository.*;
import backend.isac.repository.uipath.UiPathProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectVersionServiceImpl implements ProjectVersionService {

    private final ProjectVersionRepository projectVersionRepository;
    private final JiraIssueRepository jiraIssueRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final UiPathProcessRepository uiPathProcessRepository;
    private final DashboardRepository dashboardRepository;
    private final ProjectVersionMapper projectVersionMapper;

    @Autowired
    public ProjectVersionServiceImpl(ProjectVersionRepository projectVersionRepository, JiraIssueRepository jiraIssueRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository, UiPathProcessRepository uiPathProcessRepository, DashboardRepository dashboardRepository, ProjectVersionMapper projectVersionMapper) {
        this.projectVersionRepository = projectVersionRepository;
        this.jiraIssueRepository = jiraIssueRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.uiPathProcessRepository = uiPathProcessRepository;
        this.dashboardRepository = dashboardRepository;
        this.projectVersionMapper = projectVersionMapper;
    }

    @Override
    public ProjectVersionDTO createProjectVersion(ProjectVersionDTO projectVersionDTO) {
        ProjectVersion projectVersion = projectVersionMapper.toEntity(projectVersionDTO);
        projectVersion.setCreatedDate(LocalDateTime.now());
        mapProjectVersionRelations(projectVersion, projectVersionDTO);
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

        mapProjectVersionRelations(projectVersion, projectVersionDTO);

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
        return convertToDtoWithRelations(projectVersion);
    }

    @Override
    public List<ProjectVersionDTO> getAllProjectVersions() {
        return projectVersionRepository.findAll().stream()
                .map(this::convertToDtoWithRelations)
                .collect(Collectors.toList());
    }

    private void mapProjectVersionRelations(ProjectVersion projectVersion, ProjectVersionDTO projectVersionDTO) {
        projectVersion.setJiraIssues(jiraIssueRepository.findAllById(projectVersionDTO.getJiraIssueIds()));
        projectVersion.setProject(projectRepository.findById(projectVersionDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectVersionDTO.getProjectId())));
        projectVersion.setEmployees(employeeRepository.findAllById(projectVersionDTO.getEmployeeIds()));
        projectVersion.setUipathProcesses(uiPathProcessRepository.findAllById(projectVersionDTO.getUipathProcessIds()));
        projectVersion.setDashboards(dashboardRepository.findAllById(projectVersionDTO.getDashboardIds()));
    }

    private ProjectVersionDTO convertToDtoWithRelations(ProjectVersion projectVersion) {
        ProjectVersionDTO projectVersionDTO = projectVersionMapper.toDTO(projectVersion);
        projectVersionDTO.setJiraIssueIds(projectVersion.getJiraIssues().stream().map(JiraIssue::getId).collect(Collectors.toList()));
        projectVersionDTO.setEmployeeIds(projectVersion.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        projectVersionDTO.setUipathProcessIds(projectVersion.getUipathProcesses().stream().map(UiPathProcess::getId).collect(Collectors.toList()));
        return projectVersionDTO;
    }
}
