package backend.isac.service;

import backend.isac.dto.DashboardDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.DashboardMapper;
import backend.isac.model.Dashboard;
import backend.isac.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;
    private final DashboardMapper dashboardMapper;

    @Autowired
    public DashboardServiceImpl(DashboardRepository dashboardRepository, DashboardMapper dashboardMapper) {
        this.dashboardRepository = dashboardRepository;
        this.dashboardMapper = dashboardMapper;
    }

    @Override
    public List<DashboardDTO> getAllDashboards() {
        return dashboardRepository.findAll().stream().map(dashboardMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DashboardDTO getDashboardById(Long id) {
        Dashboard dashboard = dashboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dashboard not found with id " + id));
        return dashboardMapper.toDTO(dashboard);
    }

    @Override
    public DashboardDTO createDashboard(DashboardDTO dashboardDTO) {
        Dashboard dashboard = dashboardMapper.toEntity(dashboardDTO);
        Dashboard savedDashboard = dashboardRepository.save(dashboard);
        return dashboardMapper.toDTO(savedDashboard);
    }

    @Override
    public DashboardDTO updateDashboard(Long id, DashboardDTO dashboardDTO) {
        Dashboard dashboard = dashboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dashboard not found with id " + id));
        dashboard.setDashboardName(dashboardDTO.getDashboardName());
        dashboard.setUrl(dashboardDTO.getUrl());
        Dashboard updatedDashboard = dashboardRepository.save(dashboard);
        return dashboardMapper.toDTO(updatedDashboard);
    }

    @Override
    public void deleteDashboard(Long id) {
        if (!dashboardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dashboard not found with id " + id);
        }
        dashboardRepository.deleteById(id);
    }
}
