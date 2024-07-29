package backend.isac.dto;

import backend.isac.model.enums.ECompletionLevel;
import backend.isac.model.enums.EDocumentType;
import backend.isac.model.enums.ELanguage;
import lombok.Data;

@Data
public class DocumentDTO {
    private Long id;
    private String name;
    private String url;
    private String comment;
    private ELanguage language;
    private EDocumentType documentType;
    private ECompletionLevel completionLevel;
    private Long projectVersionId;
}
