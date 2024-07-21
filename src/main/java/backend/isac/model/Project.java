package backend.isac.model;

import backend.isac.model.enums.EAsset;
import backend.isac.model.enums.EImpact;
import backend.isac.model.enums.ESupportTeam;
import backend.isac.model.enums.EStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "project", uniqueConstraints = @UniqueConstraint(columnNames = "projectCode"))
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String projectCode;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "iua_code_id", nullable = false)
    private IUACode iuaCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EImpact impact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ESupportTeam supportTeam;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EAsset asset;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatus status;
}