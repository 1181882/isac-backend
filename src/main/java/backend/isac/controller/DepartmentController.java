package backend.isac.controller;
import backend.isac.dto.DepartmentDTO;
import backend.isac.dto.ResponseMessageDTO;
import backend.isac.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO department) {
        return departmentService.save(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessageDTO("Department deleted successfully!"));
    }
}
