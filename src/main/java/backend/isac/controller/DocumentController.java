package backend.isac.controller;

import backend.isac.dto.DocumentDTO;
import backend.isac.dto.ResponseMessageDTO;
import backend.isac.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<DocumentDTO> getAllDocuments() {
        return documentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        DocumentDTO document = documentService.findById(id);
        return ResponseEntity.ok(document);
    }

    @PostMapping
    public DocumentDTO createDocument(@RequestBody DocumentDTO documentDTO) {
        return documentService.save(documentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteDocument(@PathVariable Long id) {
        documentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessageDTO("Document deleted successfully!"));
    }
}