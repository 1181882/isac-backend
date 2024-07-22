package backend.isac.service;

import backend.isac.dto.DashboardDTO;
import backend.isac.model.Dashboard;

import java.util.List;

public interface DashboardService {
    List<DashboardDTO> getAllDashboards();

    DashboardDTO getDashboardById(Long id);

    DashboardDTO createDashboard(DashboardDTO dashboardDTO);

    DashboardDTO updateDashboard(Long id, DashboardDTO dashboardDTO);

    void deleteDashboard(Long id);
}