package backend.isac.controller;

import backend.isac.dto.JiraIssueDTO;
import backend.isac.service.JiraIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jira-issues")
public class JiraIssueController {

    private final JiraIssueService jiraIssueService;

    @Autowired
    public JiraIssueController(JiraIssueService jiraIssueService) {
        this.jiraIssueService = jiraIssueService;
    }

    @PostMapping
    public ResponseEntity<JiraIssueDTO> createJiraIssue(@RequestBody JiraIssueDTO jiraIssueDTO) {
        JiraIssueDTO createdIssue = jiraIssueService.createJiraIssue(jiraIssueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIssue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JiraIssueDTO> updateJiraIssue(@PathVariable Long id, @RequestBody JiraIssueDTO jiraIssueDTO) {
        JiraIssueDTO updatedIssue = jiraIssueService.updateJiraIssue(id, jiraIssueDTO);
        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJiraIssue(@PathVariable Long id) {
        jiraIssueService.deleteJiraIssue(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JiraIssueDTO> getJiraIssueById(@PathVariable Long id) {
        JiraIssueDTO issue = jiraIssueService.getJiraIssueById(id);
        return ResponseEntity.ok(issue);
    }

    @GetMapping
    public ResponseEntity<List<JiraIssueDTO>> getAllJiraIssues() {
        List<JiraIssueDTO> issues = jiraIssueService.getAllJiraIssues();
        return ResponseEntity.ok(issues);
    }
}
