package ani.fraczek.controller;

import ani.fraczek.domain.dto.PostDTO;
import ani.fraczek.domain.entity.Post;
import ani.fraczek.service.PostService;
import ani.fraczek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping("posts/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getAllCurrentUserPosts() {
        List<PostDTO> postDTOS = postService.getAllPostsOfUser(userService.getCurrentUserLogin());
        return postDTOS.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(postDTOS);
    }

    @GetMapping("users/{login}/posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getAllUserPosts(@PathVariable final String login) {
        List<PostDTO> postDTOS = postService.getAllPostsOfUser(login);

        return postDTOS.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(postDTOS);
    }

    @PostMapping(value = "posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity addPost(@RequestBody @Valid final PostDTO postDTO) {
        Post addedPost = postService.createPostForCurrentUser(postDTO);
        return ResponseEntity.ok(PostDTO.ofPost(addedPost));
    }

    @DeleteMapping(value = "posts/{postId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity deleteCurrentUserPost(@PathVariable(required = true) long postId) {
        Optional<Post> deletedPost = postService.deleteCurrentUserPost(postId);
        return deletedPost.map(deleted -> ResponseEntity.ok(PostDTO.ofPost(deleted)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("users/current/timeline")
    public ResponseEntity getCurrentUserTimeline() {
        List<PostDTO> currentUserTimeline = postService.getCurrentUserTimeline();
        return currentUserTimeline.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(currentUserTimeline);
    }


}
