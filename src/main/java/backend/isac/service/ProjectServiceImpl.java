package backend.isac.service;

import backend.isac.dto.ProjectDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.ProjectMapper;
import backend.isac.model.Department;
import backend.isac.model.IUACode;
import backend.isac.model.Project;
import backend.isac.model.ProjectVersion;
import backend.isac.repository.DepartmentRepository;
import backend.isac.repository.IUACodeRepository;
import backend.isac.repository.ProjectRepository;
import backend.isac.repository.ProjectVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final IUACodeRepository iuaCodeRepository;
    private final ProjectVersionRepository projectVersionRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, DepartmentRepository departmentRepository, IUACodeRepository iuaCodeRepository, ProjectVersionRepository projectVersionRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
        this.iuaCodeRepository = iuaCodeRepository;
        this.projectVersionRepository = projectVersionRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Department department = departmentRepository.findById(projectDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + projectDTO.getDepartmentId()));
        IUACode iuaCode = iuaCodeRepository.findById(projectDTO.getIuaCodeId())
                .orElseThrow(() -> new ResourceNotFoundException("IUA Code not found with id " + projectDTO.getIuaCodeId()));

        Project project = projectMapper.toEntity(projectDTO);
        project.setDepartment(department);
        project.setIuaCode(iuaCode);
        if (projectDTO.getProjectVersionIds() != null) {
            List<ProjectVersion> projectVersions = projectVersionRepository.findAllById(projectDTO.getProjectVersionIds());
            project.setProjectVersions(projectVersions);
        }
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDTO(savedProject);
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

        Department department = departmentRepository.findById(projectDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + projectDTO.getDepartmentId()));
        IUACode iuaCode = iuaCodeRepository.findById(projectDTO.getIuaCodeId())
                .orElseThrow(() -> new ResourceNotFoundException("IUA Code not found with id " + projectDTO.getIuaCodeId()));

        project.setProjectCode(projectDTO.getProjectCode());
        project.setName(projectDTO.getName());
        project.setDepartment(department);
        project.setIuaCode(iuaCode);
        project.setImpact(projectDTO.getImpact());
        project.setSupportTeam(projectDTO.getSupportTeam());
        project.setAsset(projectDTO.getAsset());
        project.setStatus(projectDTO.getStatus());
        if (projectDTO.getProjectVersionIds() != null) {
            List<ProjectVersion> projectVersions = projectVersionRepository.findAllById(projectDTO.getProjectVersionIds());
            project.setProjectVersions(projectVersions);
        }

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDTO(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        projectRepository.delete(project);
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        ProjectDTO projectDTO = projectMapper.toDTO(project);
        if (project.getProjectVersions() != null) {
            projectDTO.setProjectVersionIds(project.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
        }
        return projectDTO;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(project -> {
                    ProjectDTO projectDTO = projectMapper.toDTO(project);
                    if (project.getProjectVersions() != null) {
                        projectDTO.setProjectVersionIds(project.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
                    }
                    return projectDTO;
                })
                .collect(Collectors.toList());
    }
}