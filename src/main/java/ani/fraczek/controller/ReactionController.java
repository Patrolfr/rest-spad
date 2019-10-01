package ani.fraczek.controller;

import ani.fraczek.domain.dto.ReactionDTO;
import ani.fraczek.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionService reactionService;

    @GetMapping(value = "/reactions")
    public ResponseEntity getAllReactions() {
        List<ReactionDTO> allReactions = reactionService.getAllReactions();

        return allReactions.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allReactions);
    }

    @GetMapping(value = "posts/{postId}/reactions")
    public ResponseEntity getReactionsOfPost(@PathVariable(required = true) Long postId) {
        List<ReactionDTO> reactionsOfPost = reactionService.getReactionsOfPost(postId);

        return reactionsOfPost.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reactionsOfPost);
    }

    @PostMapping(value = "posts/{postId}/reactions")
    public ResponseEntity addReactionForPost(@RequestBody ReactionDTO reactionDTO){
        return reactionService.addNewReactionForCurrentUser(reactionDTO)
                .map(reaction -> ResponseEntity.ok(ReactionDTO.ofReaction(reaction)))
                .orElse(ResponseEntity.badRequest().build());
    }




}
