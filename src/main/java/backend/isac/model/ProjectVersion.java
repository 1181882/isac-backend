package backend.isac.model;

import backend.isac.model.enums.ELifecycle;
import backend.isac.model.enums.EStatus;
import backend.isac.model.uipath.UiPathProcess;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "project_version")
public class ProjectVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "deploy_date_qa")
    private LocalDateTime deployDateQa;

    @Column(name = "deploy_date_prod")
    private LocalDateTime deployDateProd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ELifecycle lifecycle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatus status;

    @Embedded
    private Version version;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "project_version_jira_issues",
            joinColumns = @JoinColumn(name = "project_version_id"),
            inverseJoinColumns = @JoinColumn(name = "jira_issue_id"))
    private List<JiraIssue> jiraIssues;

    @ManyToMany
    @JoinTable(
            name = "project_version_employees",
            joinColumns = @JoinColumn(name = "project_version_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "project_version_uipath_processes",
            joinColumns = @JoinColumn(name = "project_version_id"),
            inverseJoinColumns = @JoinColumn(name = "uipath_process_id"))
    private List<UiPathProcess> uipathProcesses;

    @ManyToMany
    @JoinTable(
            name = "version_dashboard",
            joinColumns = @JoinColumn(name = "version_id"),
            inverseJoinColumns = @JoinColumn(name = "dashboard_id"))
    private List<Dashboard> dashboards;

    @OneToMany(mappedBy = "projectVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;
}
