package backend.isac.controller.uipath;

import backend.isac.dto.uipath.UiPathUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.isac.service.uipath.UiPathUserService;

import java.util.List;

@RestController
@RequestMapping("/uipath/users")
public class UiPathUserController {

    private final UiPathUserService userService;

    @Autowired
    public UiPathUserController(UiPathUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UiPathUserDTO> createUser(@RequestBody UiPathUserDTO userDTO) {
        UiPathUserDTO createdUser = userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UiPathUserDTO> updateUser(@PathVariable Long id, @RequestBody UiPathUserDTO userDTO) {
        userDTO.setId(id);
        UiPathUserDTO updatedUser = userService.save(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UiPathUserDTO> getUserById(@PathVariable Long id) {
        UiPathUserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UiPathUserDTO>> getAllUsers() {
        List<UiPathUserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}