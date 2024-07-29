package backend.isac.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "iua_codes", uniqueConstraints = @UniqueConstraint(columnNames = "abbreviation"))
public class IUACode extends BaseEntity {

    @OneToMany(mappedBy = "iuaCode", cascade = CascadeType.ALL)
    private List<Project> projects;
}
