package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathMachineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.isac.service.uipath.UiPathMachineService;

import java.util.List;

@RestController
@RequestMapping("/uipath/machines")
public class UiPathMachineController {

    private final UiPathMachineService machineService;

    @Autowired
    public UiPathMachineController(UiPathMachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    public ResponseEntity<UiPathMachineDTO> createMachine(@RequestBody UiPathMachineDTO machineDTO) {
        UiPathMachineDTO createdMachine = machineService.save(machineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMachine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathMachineDTO> updateMachine(@PathVariable Long id, @RequestBody UiPathMachineDTO machineDTO) {
        machineDTO.setId(id);
        UiPathMachineDTO updatedMachine = machineService.save(machineDTO);
        return ResponseEntity.ok(updatedMachine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        machineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathMachineDTO> getMachineById(@PathVariable Long id) {
        UiPathMachineDTO machineDTO = machineService.findById(id);
        return ResponseEntity.ok(machineDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathMachineDTO>> getAllMachines() {
        List<UiPathMachineDTO> machines = machineService.findAll();
        return ResponseEntity.ok(machines);
    }
}