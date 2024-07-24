package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathRobotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.isac.service.uipath.UiPathRobotService;

import java.util.List;

@RestController
@RequestMapping("/uipath/robots")
public class UiPathRobotController {

    private final UiPathRobotService robotService;

    @Autowired
    public UiPathRobotController(UiPathRobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping
    public ResponseEntity<UiPathRobotDTO> createRobot(@RequestBody UiPathRobotDTO robotDTO) {
        UiPathRobotDTO createdRobot = robotService.save(robotDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRobot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathRobotDTO> updateRobot(@PathVariable Long id, @RequestBody UiPathRobotDTO robotDTO) {
        robotDTO.setId(id);
        UiPathRobotDTO updatedRobot = robotService.save(robotDTO);
        return ResponseEntity.ok(updatedRobot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRobot(@PathVariable Long id) {
        robotService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathRobotDTO> getRobotById(@PathVariable Long id) {
        UiPathRobotDTO robotDTO = robotService.findById(id);
        return ResponseEntity.ok(robotDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathRobotDTO>> getAllRobots() {
        List<UiPathRobotDTO> robots = robotService.findAll();
        return ResponseEntity.ok(robots);
    }
}