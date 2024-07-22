package backend.isac.controller;

import backend.isac.dto.ProcessDTO;
import backend.isac.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processes")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @GetMapping
    public List<ProcessDTO> getAllProcesses() {
        return processService.getAllProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDTO> getProcessById(@PathVariable Long id) {
        ProcessDTO processDTO = processService.getProcessById(id);
        return ResponseEntity.ok(processDTO);
    }

    @PostMapping
    public ResponseEntity<ProcessDTO> createProcess(@RequestBody ProcessDTO processDTO) {
        ProcessDTO createdProcess = processService.createProcess(processDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcess);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessDTO> updateProcess(@PathVariable Long id, @RequestBody ProcessDTO processDTO) {
        ProcessDTO updatedProcess = processService.updateProcess(id, processDTO);
        return ResponseEntity.ok(updatedProcess);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processService.deleteProcess(id);
        return ResponseEntity.noContent().build();
    }
}
