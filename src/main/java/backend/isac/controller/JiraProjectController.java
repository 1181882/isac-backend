package backend.isac.controller;

import backend.isac.dto.JiraProjectDTO;
import backend.isac.service.JiraProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jira-projects")
public class JiraProjectController {

    private final JiraProjectService jiraProjectService;

    @Autowired
    public JiraProjectController(JiraProjectService jiraProjectService) {
        this.jiraProjectService = jiraProjectService;
    }

    @PostMapping
    public ResponseEntity<JiraProjectDTO> createJiraProject(@RequestBody JiraProjectDTO jiraProjectDTO) {
        JiraProjectDTO createdProject = jiraProjectService.createJiraProject(jiraProjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JiraProjectDTO> updateJiraProject(@PathVariable Long id, @RequestBody JiraProjectDTO jiraProjectDTO) {
        JiraProjectDTO updatedProject = jiraProjectService.updateJiraProject(id, jiraProjectDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJiraProject(@PathVariable Long id) {
        jiraProjectService.deleteJiraProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JiraProjectDTO> getJiraProjectById(@PathVariable Long id) {
        JiraProjectDTO project = jiraProjectService.getJiraProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<JiraProjectDTO>> getAllJiraProjects() {
        List<JiraProjectDTO> projects = jiraProjectService.getAllJiraProjects();
        return ResponseEntity.ok(projects);
    }
}
