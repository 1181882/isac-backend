package backend.isac.model;

import backend.isac.model.enums.EJiraIssueType;
import backend.isac.model.enums.EJiraStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "jira_issues")
public class JiraIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "issue_type", nullable = false)
    private EJiraIssueType issueType;

    @Enumerated(EnumType.STRING)
    @Column(name = "issue_status", nullable = false)
    private EJiraStatus issueStatus;

    @ManyToOne
    @JoinColumn(name = "jira_project_id", nullable = false)
    private JiraProject jiraProject;

    @ManyToMany(mappedBy = "jiraIssues")
    private List<ProjectVersion> projectVersions;
}
