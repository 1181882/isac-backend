package backend.isac.model.uipath;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_user")
public class UiPathUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String surname;
    private String name;
    private String emailAddress;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private UiPathTenant tenant;
}