package ani.fraczek.service;

import ani.fraczek.domain.dto.ReactionDTO;
import ani.fraczek.repository.PostRepository;
import ani.fraczek.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final UserService userService;

    private final ReactionRepository reactionRepository;

    private final PostRepository postRepository;


    public List<ReactionDTO> getAllReactions(){
        return reactionRepository.findAll().stream()
                .map(ReactionDTO::ofReaction)
                .collect(Collectors.toList());
    }

    public List<ReactionDTO> getReactionsOfPost(Long postId){
        return postRepository.findById(postId).map(post ->
                reactionRepository.findAllByPost(post).stream()
                        .map(ReactionDTO::ofReaction).collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("PostNotFound"));
    }


}
