package backend.isac.controller;

import backend.isac.dto.CompanyDTO;
import backend.isac.dto.ResponseMessageDTO;
import backend.isac.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        CompanyDTO company = companyService.findById(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.save(companyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessageDTO("Company deleted successfully!"));
    }
}