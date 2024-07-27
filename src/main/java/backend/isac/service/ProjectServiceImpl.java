package backend.isac.service;

import backend.isac.dto.ProjectDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.ProjectMapper;
import backend.isac.model.Department;
import backend.isac.model.IUACode;
import backend.isac.model.Project;
import backend.isac.repository.DepartmentRepository;
import backend.isac.repository.IUACodeRepository;
import backend.isac.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final IUACodeRepository iuaCodeRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, DepartmentRepository departmentRepository, IUACodeRepository iuaCodeRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
        this.iuaCodeRepository = iuaCodeRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Department department = departmentRepository.findById(projectDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + projectDTO.getDepartmentId()));
        IUACode iuaCode = iuaCodeRepository.findById(projectDTO.getIuaCodeId())
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + projectDTO.getIuaCodeId()));

        Project project = projectMapper.toEntity(projectDTO);
        project.setDepartment(department);
        project.setIuaCode(iuaCode);
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
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + projectDTO.getIuaCodeId()));

        project.setProjectCode(projectDTO.getProjectCode());
        project.setName(projectDTO.getName());
        project.setDepartment(department);
        project.setIuaCode(iuaCode);
        project.setImpact(projectDTO.getImpact());
        project.setSupportTeam(projectDTO.getSupportTeam());
        project.setAsset(projectDTO.getAsset());
        project.setStatus(projectDTO.getStatus());

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
        return projectMapper.toDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }
}