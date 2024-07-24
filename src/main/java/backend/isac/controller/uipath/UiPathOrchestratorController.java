package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathOrchestratorDTO;
import backend.isac.service.uipath.UiPathOrchestratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/orchestrators")
public class UiPathOrchestratorController {

    private final UiPathOrchestratorService orchestratorService;

    @Autowired
    public UiPathOrchestratorController(UiPathOrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @PostMapping
    public ResponseEntity<UiPathOrchestratorDTO> createOrchestrator(@RequestBody UiPathOrchestratorDTO orchestratorDTO) {
        UiPathOrchestratorDTO createdOrchestrator = orchestratorService.save(orchestratorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrchestrator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathOrchestratorDTO> updateOrchestrator(@PathVariable Long id, @RequestBody UiPathOrchestratorDTO orchestratorDTO) {
        orchestratorDTO.setId(id);
        UiPathOrchestratorDTO updatedOrchestrator = orchestratorService.save(orchestratorDTO);
        return ResponseEntity.ok(updatedOrchestrator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrchestrator(@PathVariable Long id) {
        orchestratorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathOrchestratorDTO> getOrchestratorById(@PathVariable Long id) {
        UiPathOrchestratorDTO orchestratorDTO = orchestratorService.findById(id);
        return ResponseEntity.ok(orchestratorDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathOrchestratorDTO>> getAllOrchestrators() {
        List<UiPathOrchestratorDTO> orchestrators = orchestratorService.findAll();
        return ResponseEntity.ok(orchestrators);
    }
}