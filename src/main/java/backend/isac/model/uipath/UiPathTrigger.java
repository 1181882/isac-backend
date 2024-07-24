package backend.isac.model.uipath;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_trigger")
public class UiPathTrigger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean enabled;
    private String runtimeType;
    private String startProcessCronExpression;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private UiPathProcess process;
}