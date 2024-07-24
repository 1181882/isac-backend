package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathFolderDTO;
import backend.isac.service.uipath.UiPathFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uipath/folders")
public class UiPathFolderController {

    private final UiPathFolderService folderService;

    @Autowired
    public UiPathFolderController(UiPathFolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public ResponseEntity<UiPathFolderDTO> createFolder(@RequestBody UiPathFolderDTO folderDTO) {
        UiPathFolderDTO createdFolder = folderService.save(folderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFolder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathFolderDTO> updateFolder(@PathVariable Long id, @RequestBody UiPathFolderDTO folderDTO) {
        folderDTO.setId(id);
        UiPathFolderDTO updatedFolder = folderService.save(folderDTO);
        return ResponseEntity.ok(updatedFolder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        folderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathFolderDTO> getFolderById(@PathVariable Long id) {
        UiPathFolderDTO folderDTO = folderService.findById(id);
        return ResponseEntity.ok(folderDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathFolderDTO>> getAllFolders() {
        List<UiPathFolderDTO> folders = folderService.findAll();
        return ResponseEntity.ok(folders);
    }
}