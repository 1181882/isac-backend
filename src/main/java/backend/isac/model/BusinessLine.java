package backend.isac.model;

import backend.isac.model.enums.EEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "business_lines",uniqueConstraints = @UniqueConstraint(columnNames = "abbreviation"))
public class BusinessLine extends BaseEntity {

    @OneToMany(mappedBy = "businessLine")
    private List<Department> departments;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity", nullable = false)
    private EEntity entity;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
