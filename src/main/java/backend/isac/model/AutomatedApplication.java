package backend.isac.model;

import backend.isac.model.enums.EApplicationType;
import backend.isac.model.enums.EAutomationType;
import backend.isac.model.uipath.UiPathProcess;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "automated_applications")
public class AutomatedApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type", nullable = false)
    private EApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "automation_type", nullable = false)
    private EAutomationType automationType;

    @ManyToMany(mappedBy = "automatedApplications")
    private List<UiPathProcess> uipathProcesses;
}
