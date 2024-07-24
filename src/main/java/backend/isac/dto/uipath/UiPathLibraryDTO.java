package backend.isac.dto.uipath;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UiPathLibraryDTO {
    private Long id;
    private String name;
    private String description;
    private String version;
    private Boolean isLatestVersion;
    private String publishedBy;
    private LocalDate publishedOn;
    private Long folderId;
}
