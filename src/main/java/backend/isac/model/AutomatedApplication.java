package backend.isac.model;

import backend.isac.model.enums.EApplicationType;
import backend.isac.model.enums.EAutomationType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "automated_application")
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
}
