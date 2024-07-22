package backend.isac.controller;

import backend.isac.dto.DashboardDTO;
import backend.isac.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public List<DashboardDTO> getAllDashboards() {
        return dashboardService.getAllDashboards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DashboardDTO> getDashboardById(@PathVariable Long id) {
        DashboardDTO dashboardDTO = dashboardService.getDashboardById(id);
        return ResponseEntity.ok(dashboardDTO);
    }

    @PostMapping
    public ResponseEntity<DashboardDTO> createDashboard(@RequestBody DashboardDTO dashboardDTO) {
        DashboardDTO createdDashboard = dashboardService.createDashboard(dashboardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDashboard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DashboardDTO> updateDashboard(@PathVariable Long id, @RequestBody DashboardDTO dashboardDTO) {
        DashboardDTO updatedDashboard = dashboardService.updateDashboard(id, dashboardDTO);
        return ResponseEntity.ok(updatedDashboard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDashboard(@PathVariable Long id) {
        dashboardService.deleteDashboard(id);
        return ResponseEntity.noContent().build();
    }
}