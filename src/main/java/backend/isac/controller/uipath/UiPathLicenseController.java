package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathLicenseDTO;
import backend.isac.service.uipath.UiPathLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/licenses")
public class UiPathLicenseController {

    private final UiPathLicenseService licenseService;

    @Autowired
    public UiPathLicenseController(UiPathLicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping
    public ResponseEntity<UiPathLicenseDTO> createLicense(@RequestBody UiPathLicenseDTO licenseDTO) {
        UiPathLicenseDTO createdLicense = licenseService.save(licenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLicense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathLicenseDTO> updateLicense(@PathVariable Long id, @RequestBody UiPathLicenseDTO licenseDTO) {
        licenseDTO.setId(id);
        UiPathLicenseDTO updatedLicense = licenseService.save(licenseDTO);
        return ResponseEntity.ok(updatedLicense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        licenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathLicenseDTO> getLicenseById(@PathVariable Long id) {
        UiPathLicenseDTO licenseDTO = licenseService.findById(id);
        return ResponseEntity.ok(licenseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathLicenseDTO>> getAllLicenses() {
        List<UiPathLicenseDTO> licenses = licenseService.findAll();
        return ResponseEntity.ok(licenses);
    }
}