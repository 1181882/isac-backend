package backend.isac.controller.uipath;

import backend.isac.dto.uipath.*;
import backend.isac.service.uipath.UiPathEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/environments")
public class UiPathEnvironmentController {

    private final UiPathEnvironmentService environmentService;

    @Autowired
    public UiPathEnvironmentController(UiPathEnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping
    public ResponseEntity<UiPathEnvironmentDTO> createEnvironment(@RequestBody UiPathEnvironmentDTO environmentDTO) {
        UiPathEnvironmentDTO createdEnvironment = environmentService.save(environmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnvironment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathEnvironmentDTO> updateEnvironment(@PathVariable Long id, @RequestBody UiPathEnvironmentDTO environmentDTO) {
        environmentDTO.setId(id);
        UiPathEnvironmentDTO updatedEnvironment = environmentService.save(environmentDTO);
        return ResponseEntity.ok(updatedEnvironment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvironment(@PathVariable Long id) {
        environmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathEnvironmentDTO> getEnvironmentById(@PathVariable Long id) {
        UiPathEnvironmentDTO environmentDTO = environmentService.findById(id);
        return ResponseEntity.ok(environmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathEnvironmentDTO>> getAllEnvironments() {
        List<UiPathEnvironmentDTO> environments = environmentService.findAll();
        return ResponseEntity.ok(environments);
    }
}