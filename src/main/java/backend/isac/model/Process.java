package backend.isac.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "process_name", nullable = false)
    private String processName;
}