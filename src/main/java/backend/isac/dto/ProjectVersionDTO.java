package backend.isac.dto;

import backend.isac.model.Version;
import backend.isac.model.enums.ELifecycle;
import backend.isac.model.enums.EStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProjectVersionDTO {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime deployDateQa;
    private LocalDateTime deployDateProd;
    private ELifecycle lifecycle;
    private EStatus status;
    private Version version;
    private List<Long> jiraIssueIds;
    private Long projectId;
    private List<Long> employeeIds;
    private List<Long> uipathProcessIds;
    private List<Long> dashboardIds;
}
