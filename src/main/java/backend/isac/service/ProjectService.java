package backend.isac.service;

import backend.isac.dto.ProjectDTO;
import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO updateProject(Long id, ProjectDTO projectDTO);
    void deleteProject(Long id);
    ProjectDTO getProjectById(Long id);
    List<ProjectDTO> getAllProjects();
}
