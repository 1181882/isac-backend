package backend.isac.service;

import backend.isac.dto.JiraProjectDTO;

import java.util.List;

public interface JiraProjectService {
    JiraProjectDTO createJiraProject(JiraProjectDTO jiraProjectDTO);

    JiraProjectDTO updateJiraProject(Long id, JiraProjectDTO jiraProjectDTO);

    void deleteJiraProject(Long id);

    JiraProjectDTO getJiraProjectById(Long id);

    List<JiraProjectDTO> getAllJiraProjects();
}
