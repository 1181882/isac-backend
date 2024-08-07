package backend.isac.model.uipath;

import backend.isac.model.Version;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_machines")
public class UiPathMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Version version;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private UiPathTenant tenant;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UiPathRobot> robots;
}
