package backend.isac.model.uipath;

import backend.isac.model.enums.EEnvironmentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_orchestrator")
public class UiPathOrchestrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String authToken;

    @Enumerated(EnumType.STRING)
    private EEnvironmentType environmentType;

    @OneToMany(mappedBy = "orchestrator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UiPathTenant> tenants;
}