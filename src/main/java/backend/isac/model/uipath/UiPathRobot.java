package backend.isac.model.uipath;

import backend.isac.model.Version;
import backend.isac.model.enums.EEnvironmentType;
import backend.isac.model.enums.EUipathLicenseType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_robots")
public class UiPathRobot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Version version;

    @Enumerated(EnumType.STRING)
    private EUipathLicenseType licenseType;

    @Enumerated(EnumType.STRING)
    private EEnvironmentType environmentType;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private UiPathMachine machine;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private UiPathTenant tenant;
}
