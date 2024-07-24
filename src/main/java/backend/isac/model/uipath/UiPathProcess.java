package backend.isac.model.uipath;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_process")
public class UiPathProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "key_column")
    private String keyColumn; // Renomeie a coluna para evitar conflito com palavras reservadas

    private String version;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private UiPathFolder folder;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UiPathTrigger> triggers;
}