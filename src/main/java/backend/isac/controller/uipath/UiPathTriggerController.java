package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathTriggerDTO;
import backend.isac.service.uipath.UiPathTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/triggers")
public class UiPathTriggerController {

    private final UiPathTriggerService triggerService;

    @Autowired
    public UiPathTriggerController(UiPathTriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @PostMapping
    public ResponseEntity<UiPathTriggerDTO> createTrigger(@RequestBody UiPathTriggerDTO triggerDTO) {
        UiPathTriggerDTO createdTrigger = triggerService.save(triggerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrigger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathTriggerDTO> updateTrigger(@PathVariable Long id, @RequestBody UiPathTriggerDTO triggerDTO) {
        triggerDTO.setId(id);
        UiPathTriggerDTO updatedTrigger = triggerService.save(triggerDTO);
        return ResponseEntity.ok(updatedTrigger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrigger(@PathVariable Long id) {
        triggerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathTriggerDTO> getTriggerById(@PathVariable Long id) {
        UiPathTriggerDTO triggerDTO = triggerService.findById(id);
        return ResponseEntity.ok(triggerDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathTriggerDTO>> getAllTriggers() {
        List<UiPathTriggerDTO> triggers = triggerService.findAll();
        return ResponseEntity.ok(triggers);
    }
}