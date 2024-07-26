package backend.isac.model.uipath;

import backend.isac.model.Version;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_robot")
public class UiPathRobot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Version version;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private UiPathMachine machine;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private UiPathTenant tenant;

    @ManyToOne
    @JoinColumn(name = "environment_id")
    private UiPathEnvironment environment;
}
