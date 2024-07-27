package backend.isac.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = "abbreviation"))
public class Department extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "business_line_id", nullable = false)
    private BusinessLine businessLine;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;
}
