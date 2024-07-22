package backend.isac.service;

import backend.isac.dto.JiraIssueDTO;

import java.util.List;

public interface JiraIssueService {
    JiraIssueDTO createJiraIssue(JiraIssueDTO jiraIssueDTO);

    JiraIssueDTO updateJiraIssue(Long id, JiraIssueDTO jiraIssueDTO);

    void deleteJiraIssue(Long id);

    JiraIssueDTO getJiraIssueById(Long id);

    List<JiraIssueDTO> getAllJiraIssues();
}