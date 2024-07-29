package backend.isac.dto;

import backend.isac.model.enums.EJiraIssueType;
import backend.isac.model.enums.EJiraStatus;
import lombok.Data;

import java.util.List;

@Data
public class JiraIssueDTO {
    private Long id;
    private String name;
    private EJiraIssueType issueType;
    private EJiraStatus issueStatus;
    private Long jiraProjectId;
    private List<Long> projectVersionIds;
}
