package ani.fraczek.controller;

import ani.fraczek.domain.dto.UserDTO;
import ani.fraczek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // check lazy loading of roles
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        List<UserDTO> userDTOS = userService.retrieveAll();
        return userDTOS.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(userDTOS);
    }

}
