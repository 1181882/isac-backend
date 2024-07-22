package backend.isac.controller;

import backend.isac.dto.ProjectVersionDTO;
import backend.isac.service.ProjectVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-versions")
public class ProjectVersionController {

    private final ProjectVersionService projectVersionService;

    @Autowired
    public ProjectVersionController(ProjectVersionService projectVersionService) {
        this.projectVersionService = projectVersionService;
    }

    @PostMapping
    public ResponseEntity<ProjectVersionDTO> createProjectVersion(@RequestBody ProjectVersionDTO projectVersionDTO) {
        ProjectVersionDTO createdProjectVersion = projectVersionService.createProjectVersion(projectVersionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProjectVersion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectVersionDTO> updateProjectVersion(@PathVariable Long id, @RequestBody ProjectVersionDTO projectVersionDTO) {
        ProjectVersionDTO updatedProjectVersion = projectVersionService.updateProjectVersion(id, projectVersionDTO);
        return ResponseEntity.ok(updatedProjectVersion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectVersion(@PathVariable Long id) {
        projectVersionService.deleteProjectVersion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectVersionDTO> getProjectVersionById(@PathVariable Long id) {
        ProjectVersionDTO projectVersion = projectVersionService.getProjectVersionById(id);
        return ResponseEntity.ok(projectVersion);
    }

    @GetMapping
    public ResponseEntity<List<ProjectVersionDTO>> getAllProjectVersions() {
        List<ProjectVersionDTO> projectVersions = projectVersionService.getAllProjectVersions();
        return ResponseEntity.ok(projectVersions);
    }
}