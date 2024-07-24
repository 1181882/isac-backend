package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.isac.service.uipath.UiPathProcessService;

import java.util.List;

@RestController
@RequestMapping("/uipath/processes")
public class UiPathProcessController {

    private final UiPathProcessService processService;

    @Autowired
    public UiPathProcessController(UiPathProcessService processService) {
        this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<UiPathProcessDTO> createProcess(@RequestBody UiPathProcessDTO processDTO) {
        UiPathProcessDTO createdProcess = processService.save(processDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcess);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathProcessDTO> updateProcess(@PathVariable Long id, @RequestBody UiPathProcessDTO processDTO) {
        processDTO.setId(id);
        UiPathProcessDTO updatedProcess = processService.save(processDTO);
        return ResponseEntity.ok(updatedProcess);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathProcessDTO> getProcessById(@PathVariable Long id) {
        UiPathProcessDTO processDTO = processService.findById(id);
        return ResponseEntity.ok(processDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathProcessDTO>> getAllProcesses() {
        List<UiPathProcessDTO> processes = processService.findAll();
        return ResponseEntity.ok(processes);
    }
}