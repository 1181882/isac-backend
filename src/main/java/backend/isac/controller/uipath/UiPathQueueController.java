package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathQueueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.isac.service.uipath.UiPathQueueService;

import java.util.List;

@RestController
@RequestMapping("/uipath/queues")
public class UiPathQueueController {

    private final UiPathQueueService queueService;

    @Autowired
    public UiPathQueueController(UiPathQueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping
    public ResponseEntity<UiPathQueueDTO> createQueue(@RequestBody UiPathQueueDTO queueDTO) {
        UiPathQueueDTO createdQueue = queueService.save(queueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQueue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathQueueDTO> updateQueue(@PathVariable Long id, @RequestBody UiPathQueueDTO queueDTO) {
        queueDTO.setId(id);
        UiPathQueueDTO updatedQueue = queueService.save(queueDTO);
        return ResponseEntity.ok(updatedQueue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQueue(@PathVariable Long id) {
        queueService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathQueueDTO> getQueueById(@PathVariable Long id) {
        UiPathQueueDTO queueDTO = queueService.findById(id);
        return ResponseEntity.ok(queueDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathQueueDTO>> getAllQueues() {
        List<UiPathQueueDTO> queues = queueService.findAll();
        return ResponseEntity.ok(queues);
    }
}