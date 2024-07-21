package backend.isac.controller;

import backend.isac.dto.EmployeeDTO;
import backend.isac.dto.ResponseMessageDTO;
import backend.isac.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessageDTO("Employee deleted successfully!"));
    }
}