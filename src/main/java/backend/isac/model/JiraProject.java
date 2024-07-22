package backend.isac.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "jira_project")
public class JiraProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "jiraProject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JiraIssue> jiraIssues;
}
