package ani.fraczek.controller;

import ani.fraczek.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @PatchMapping("users/current/followee/{followeeLogin}")
    public ResponseEntity addFolloweeForCurrentUser(@PathVariable final String followeeLogin){
        followerService.addFolloweeForCurrentUser(followeeLogin);
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/current/followees")
    public ResponseEntity getCurrentUserFollowees(){
        Set<String> currentUserFollowees = followerService.getCurrentUserFollowees();
        return currentUserFollowees.isEmpty() ?
               ResponseEntity.noContent().build() :
               ResponseEntity.ok(currentUserFollowees);
    }

}
