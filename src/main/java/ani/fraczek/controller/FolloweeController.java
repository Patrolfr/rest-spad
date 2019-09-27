package ani.fraczek.controller;

import ani.fraczek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolloweeController {

    private final UserService userService;


    @PatchMapping("users/current/followee/{followeeLogin}")
    public ResponseEntity addFolloweeForCurrentUser(@PathVariable String followeeLogin){
        return ResponseEntity.badRequest().build();
    }

}
