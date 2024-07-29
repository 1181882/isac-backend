package backend.isac.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies",uniqueConstraints = @UniqueConstraint(columnNames = "abbreviation"))
public class Company extends BaseEntity {

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<BusinessLine> businessLines;

}
