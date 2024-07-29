package backend.isac.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dashboards")
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dashboard_name", nullable = false)
    private String dashboardName;

    @Column(name = "url", nullable = false)
    private String url;
}