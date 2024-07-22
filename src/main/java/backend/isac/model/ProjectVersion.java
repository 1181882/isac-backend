package backend.isac.model;

import backend.isac.model.enums.ELifecycle;
import backend.isac.model.enums.EStatus;
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
    @Column(name = "lifecycle", nullable = false)
    private ELifecycle lifecycle;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EStatus status;

    @Embedded
    private Version version;

    @ManyToMany
    @JoinTable(
            name = "version_process",
            joinColumns = @JoinColumn(name = "version_id"),
            inverseJoinColumns = @JoinColumn(name = "process_id"))
    private List<Process> processes;

    @ManyToMany
    @JoinTable(
            name = "version_dashboard",
            joinColumns = @JoinColumn(name = "version_id"),
            inverseJoinColumns = @JoinColumn(name = "dashboard_id"))
    private List<Dashboard> dashboards;
}