package backend.isac.model.uipath;

import backend.isac.model.Version;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "uipath_libraries")
public class UiPathLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Version version;
    private Boolean isLatestVersion;
    private String publishedBy;
    private LocalDate publishedOn;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private UiPathFolder folder;
}
