package backend.isac.controller;

import backend.isac.dto.IUACodeDTO;
import backend.isac.service.IUACodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iua-codes")
public class IUACodeController {

    private final IUACodeService iuaCodeService;

    @Autowired
    public IUACodeController(IUACodeService iuaCodeService) {
        this.iuaCodeService = iuaCodeService;
    }

    @PostMapping
    public ResponseEntity<IUACodeDTO> createIUACode(@RequestBody IUACodeDTO iuaCodeDTO) {
        IUACodeDTO createdIUACode = iuaCodeService.createIUACode(iuaCodeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIUACode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IUACodeDTO> updateIUACode(@PathVariable Long id, @RequestBody IUACodeDTO iuaCodeDTO) {
        IUACodeDTO updatedIUACode = iuaCodeService.updateIUACode(id, iuaCodeDTO);
        return ResponseEntity.ok(updatedIUACode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIUACode(@PathVariable Long id) {
        iuaCodeService.deleteIUACode(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IUACodeDTO> getIUACodeById(@PathVariable Long id) {
        IUACodeDTO iuaCodeDTO = iuaCodeService.getIUACodeById(id);
        return ResponseEntity.ok(iuaCodeDTO);
    }

    @GetMapping
    public ResponseEntity<List<IUACodeDTO>> getAllIUACodes() {
        List<IUACodeDTO> iuaCodes = iuaCodeService.getAllIUACodes();
        return ResponseEntity.ok(iuaCodes);
    }
}
