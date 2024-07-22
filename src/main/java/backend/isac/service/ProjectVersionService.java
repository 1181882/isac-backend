package backend.isac.service;

import backend.isac.dto.ProjectVersionDTO;

import java.util.List;

public interface ProjectVersionService {
    ProjectVersionDTO createProjectVersion(ProjectVersionDTO projectVersionDTO);

    ProjectVersionDTO updateProjectVersion(Long id, ProjectVersionDTO projectVersionDTO);

    void deleteProjectVersion(Long id);

    ProjectVersionDTO getProjectVersionById(Long id);

    List<ProjectVersionDTO> getAllProjectVersions();
}
