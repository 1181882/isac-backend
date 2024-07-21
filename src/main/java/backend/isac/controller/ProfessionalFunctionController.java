package backend.isac.controller;

import backend.isac.dto.ProfessionalFunctionDTO;
import backend.isac.service.ProfessionalFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professional-functions")
public class ProfessionalFunctionController {

    private final ProfessionalFunctionService professionalFunctionService;

    @Autowired
    public ProfessionalFunctionController(ProfessionalFunctionService professionalFunctionService) {
        this.professionalFunctionService = professionalFunctionService;
    }

    @PostMapping
    public ResponseEntity<ProfessionalFunctionDTO> createProfessionalFunction(@RequestBody ProfessionalFunctionDTO professionalFunctionDTO) {
        ProfessionalFunctionDTO createdFunction = professionalFunctionService.createProfessionalFunction(professionalFunctionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFunction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalFunctionDTO> updateProfessionalFunction(@PathVariable Long id, @RequestBody ProfessionalFunctionDTO professionalFunctionDTO) {
        ProfessionalFunctionDTO updatedFunction = professionalFunctionService.updateProfessionalFunction(id, professionalFunctionDTO);
        return ResponseEntity.ok(updatedFunction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessionalFunction(@PathVariable Long id) {
        professionalFunctionService.deleteProfessionalFunction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalFunctionDTO> getProfessionalFunctionById(@PathVariable Long id) {
        ProfessionalFunctionDTO functionDTO = professionalFunctionService.getProfessionalFunctionById(id);
        return ResponseEntity.ok(functionDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalFunctionDTO>> getAllProfessionalFunctions() {
        List<ProfessionalFunctionDTO> functions = professionalFunctionService.getAllProfessionalFunctions();
        return ResponseEntity.ok(functions);
    }
}
