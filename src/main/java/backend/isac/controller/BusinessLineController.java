package backend.isac.controller;

import backend.isac.dto.BusinessLineDTO;
import backend.isac.dto.ResponseMessageDTO;
import backend.isac.service.BusinessLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business-lines")
public class BusinessLineController {

    @Autowired
    private BusinessLineService businessLineService;

    @GetMapping
    public List<BusinessLineDTO> getAllBusinessLines() {
        return businessLineService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessLineDTO> getBusinessLineById(@PathVariable Long id) {
        BusinessLineDTO businessLine = businessLineService.findById(id);
        return ResponseEntity.ok(businessLine);
    }

    @PostMapping
    public BusinessLineDTO createBusinessLine(@RequestBody BusinessLineDTO businessLine) {
        return businessLineService.save(businessLine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteBusinessLine(@PathVariable Long id) {
        businessLineService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessageDTO("BusinessLine deleted successfully!"));
    }
}