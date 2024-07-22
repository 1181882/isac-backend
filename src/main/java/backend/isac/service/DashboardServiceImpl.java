package backend.isac.service;

import backend.isac.dto.DashboardDTO;
import backend.isac.model.Dashboard;
import backend.isac.repository.DashboardRepository;
import backend.isac.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public List<DashboardDTO> getAllDashboards() {
        return dashboardRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DashboardDTO getDashboardById(Long id) {
        Dashboard dashboard = dashboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dashboard not found with id " + id));
        return toDTO(dashboard);
    }

    @Override
    public DashboardDTO createDashboard(DashboardDTO dashboardDTO) {
        Dashboard dashboard = toEntity(dashboardDTO);
        Dashboard savedDashboard = dashboardRepository.save(dashboard);
        return toDTO(savedDashboard);
    }

    @Override
    public DashboardDTO updateDashboard(Long id, DashboardDTO dashboardDTO) {
        Dashboard dashboard = dashboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dashboard not found with id " + id));
        dashboard.setDashboardName(dashboardDTO.getDashboardName());
        dashboard.setUrl(dashboardDTO.getUrl());
        Dashboard updatedDashboard = dashboardRepository.save(dashboard);
        return toDTO(updatedDashboard);
    }

    @Override
    public void deleteDashboard(Long id) {
        if (!dashboardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dashboard not found with id " + id);
        }
        dashboardRepository.deleteById(id);
    }

    private Dashboard toEntity(DashboardDTO dto) {
        Dashboard dashboard = new Dashboard();
        dashboard.setId(dto.getId());
        dashboard.setDashboardName(dto.getDashboardName());
        dashboard.setUrl(dto.getUrl());
        return dashboard;
    }

    private DashboardDTO toDTO(Dashboard dashboard) {
        DashboardDTO dto = new DashboardDTO();
        dto.setId(dashboard.getId());
        dto.setDashboardName(dashboard.getDashboardName());
        dto.setUrl(dashboard.getUrl());
        return dto;
    }
}