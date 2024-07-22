package backend.isac.dto;

import lombok.Data;

@Data
public class DashboardDTO {
    private Long id;
    private String dashboardName;
    private String url;
}