package backend.isac.service;

import backend.isac.dto.ProjectDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.IUACode;
import backend.isac.model.Project;
import backend.isac.repository.IUACodeRepository;
import backend.isac.repository.ProjectRepository;
import backend.isac.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final IUACodeRepository iuaCodeRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, IUACodeRepository iuaCodeRepository) {
        this.projectRepository = projectRepository;
        this.iuaCodeRepository = iuaCodeRepository;
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        IUACode iuaCode = iuaCodeRepository.findById(projectDTO.getIuaCodeId())
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + projectDTO.getIuaCodeId()));

        Project project = toEntity(projectDTO, iuaCode);
        Project savedProject = projectRepository.save(project);
        return toDTO(savedProject);
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

        IUACode iuaCode = iuaCodeRepository.findById(projectDTO.getIuaCodeId())
                .orElseThrow(() -> new ResourceNotFoundException("IUA Code not found with id " + projectDTO.getIuaCodeId()));

        project.setProjectCode(projectDTO.getProjectCode());
        project.setName(projectDTO.getName());
        project.setIuaCode(iuaCode);
        project.setImpact(projectDTO.getImpact());
        project.setSupportTeam(projectDTO.getSupportTeam());
        project.setAsset(projectDTO.getAsset());
        project.setStatus(projectDTO.getStatus());

        Project updatedProject = projectRepository.save(project);
        return toDTO(updatedProject);
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
        return toDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setProjectCode(project.getProjectCode());
        dto.setName(project.getName());
        dto.setIuaCodeId(project.getIuaCode().getId());
        dto.setImpact(project.getImpact());
        dto.setSupportTeam(project.getSupportTeam());
        dto.setAsset(project.getAsset());
        dto.setStatus(project.getStatus());
        return dto;
    }

    private Project toEntity(ProjectDTO projectDTO, IUACode iuaCode) {
        Project project = new Project();
        project.setProjectCode(projectDTO.getProjectCode());
        project.setName(projectDTO.getName());
        project.setIuaCode(iuaCode);
        project.setImpact(projectDTO.getImpact());
        project.setSupportTeam(projectDTO.getSupportTeam());
        project.setAsset(projectDTO.getAsset());
        project.setStatus(projectDTO.getStatus());
        return project;
    }
}
