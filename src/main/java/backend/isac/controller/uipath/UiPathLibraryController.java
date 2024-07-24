package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathLibraryDTO;
import backend.isac.service.uipath.UiPathLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/libraries")
public class UiPathLibraryController {

    private final UiPathLibraryService libraryService;

    @Autowired
    public UiPathLibraryController(UiPathLibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<UiPathLibraryDTO> createLibrary(@RequestBody UiPathLibraryDTO libraryDTO) {
        UiPathLibraryDTO createdLibrary = libraryService.save(libraryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathLibraryDTO> updateLibrary(@PathVariable Long id, @RequestBody UiPathLibraryDTO libraryDTO) {
        libraryDTO.setId(id);
        UiPathLibraryDTO updatedLibrary = libraryService.save(libraryDTO);
        return ResponseEntity.ok(updatedLibrary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        libraryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathLibraryDTO> getLibraryById(@PathVariable Long id) {
        UiPathLibraryDTO libraryDTO = libraryService.findById(id);
        return ResponseEntity.ok(libraryDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathLibraryDTO>> getAllLibraries() {
        List<UiPathLibraryDTO> libraries = libraryService.findAll();
        return ResponseEntity.ok(libraries);
    }
}