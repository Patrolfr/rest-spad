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

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping("posts/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getAllCurrentUserPosts() {

        List<PostDTO> postDTOS = postService.getALlPostsOfUser(userService.getCurrentDomainUser().getId());

        return postDTOS.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(postDTOS);
    }

    @GetMapping("users/{userId}/posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getAllUserPosts(@PathVariable final Long userId) {

        List<PostDTO> postDTOS = postService.getALlPostsOfUser(userId);

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

}
