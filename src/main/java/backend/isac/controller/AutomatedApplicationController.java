package backend.isac.controller;

import backend.isac.dto.AutomatedApplicationDTO;
import backend.isac.service.AutomatedApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automated-applications")
public class AutomatedApplicationController {

    private final AutomatedApplicationService automatedApplicationService;

    @Autowired
    public AutomatedApplicationController(AutomatedApplicationService automatedApplicationService) {
        this.automatedApplicationService = automatedApplicationService;
    }

    @PostMapping
    public ResponseEntity<AutomatedApplicationDTO> createAutomatedApplication(@RequestBody AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplicationDTO createdApplication = automatedApplicationService.createAutomatedApplication(automatedApplicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutomatedApplicationDTO> updateAutomatedApplication(@PathVariable Long id, @RequestBody AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplicationDTO updatedApplication = automatedApplicationService.updateAutomatedApplication(id, automatedApplicationDTO);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutomatedApplication(@PathVariable Long id) {
        automatedApplicationService.deleteAutomatedApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomatedApplicationDTO> getAutomatedApplicationById(@PathVariable Long id) {
        AutomatedApplicationDTO application = automatedApplicationService.getAutomatedApplicationById(id);
        return ResponseEntity.ok(application);
    }

    @GetMapping
    public ResponseEntity<List<AutomatedApplicationDTO>> getAllAutomatedApplications() {
        List<AutomatedApplicationDTO> applications = automatedApplicationService.getAllAutomatedApplications();
        return ResponseEntity.ok(applications);
    }
}