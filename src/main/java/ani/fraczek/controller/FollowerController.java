package ani.fraczek.controller;

import ani.fraczek.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("users/current/followee/{followeeLogin}")
    public ResponseEntity deleteFolloweeOfCurrentUser(@PathVariable(required = true) String followeeLogin){
        followerService.deleteCurrentUserFollowee(followeeLogin);
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
