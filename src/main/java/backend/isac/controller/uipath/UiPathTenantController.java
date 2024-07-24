package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathTenantDTO;
import backend.isac.service.uipath.UiPathTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/tenants")
public class UiPathTenantController {

    private final UiPathTenantService tenantService;

    @Autowired
    public UiPathTenantController(UiPathTenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<UiPathTenantDTO> createTenant(@RequestBody UiPathTenantDTO tenantDTO) {
        UiPathTenantDTO createdTenant = tenantService.save(tenantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathTenantDTO> updateTenant(@PathVariable Long id, @RequestBody UiPathTenantDTO tenantDTO) {
        tenantDTO.setId(id);
        UiPathTenantDTO updatedTenant = tenantService.save(tenantDTO);
        return ResponseEntity.ok(updatedTenant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathTenantDTO> getTenantById(@PathVariable Long id) {
        UiPathTenantDTO tenantDTO = tenantService.findById(id);
        return ResponseEntity.ok(tenantDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathTenantDTO>> getAllTenants() {
        List<UiPathTenantDTO> tenants = tenantService.findAll();
        return ResponseEntity.ok(tenants);
    }
}