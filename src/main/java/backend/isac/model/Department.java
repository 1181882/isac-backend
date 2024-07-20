package backend.isac.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "department",uniqueConstraints = @UniqueConstraint(columnNames = "abbreviation"))
public class Department extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "business_line_id", nullable = false)
    private BusinessLine businessLine;

}
