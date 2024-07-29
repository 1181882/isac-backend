package backend.isac.dto;

import backend.isac.model.enums.EJiraIssueType;
import lombok.Data;

import java.util.List;

@Data
public class JiraIssueDTO {
    private Long id;
    private String name;
    private EJiraIssueType issueType;
    private Long jiraProjectId;
    private List<Long> projectVersionIds;
}
