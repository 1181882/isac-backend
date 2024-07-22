package backend.isac.dto;

import lombok.Data;

import java.util.List;

@Data
public class JiraProjectDTO {
    private Long id;
    private String name;
    private List<JiraIssueDTO> jiraIssues;
}