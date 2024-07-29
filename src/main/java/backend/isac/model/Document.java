package backend.isac.model;

import backend.isac.model.enums.ECompletionLevel;
import backend.isac.model.enums.EDocumentType;
import backend.isac.model.enums.ELanguage;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ELanguage language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EDocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ECompletionLevel completionLevel;

    @ManyToOne
    @JoinColumn(name = "project_version_id", nullable = false)
    private ProjectVersion projectVersion;
}
