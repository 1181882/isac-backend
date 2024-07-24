package backend.isac.model.uipath;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_license")
public class UiPathLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseCode;
    private String licenseType;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private UiPathTenant tenant;
}