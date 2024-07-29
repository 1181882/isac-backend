package backend.isac.model.uipath;

import backend.isac.model.Version;
import backend.isac.model.AutomatedApplication;
import backend.isac.model.ProjectVersion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_process")
public class UiPathProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "key_column")
    private String keyColumn;

    private Version version;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private UiPathFolder folder;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UiPathTrigger> triggers;

    @ManyToMany(mappedBy = "uipathProcesses")
    private List<ProjectVersion> projectVersions;

    @ManyToMany
    @JoinTable(
            name = "uipath_process_automated_application",
            joinColumns = @JoinColumn(name = "uipath_process_id"),
            inverseJoinColumns = @JoinColumn(name = "automated_application_id")
    )
    private List<AutomatedApplication> automatedApplications;
}
