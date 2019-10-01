package ani.fraczek.service;

import ani.fraczek.domain.dto.ReactionDTO;
import ani.fraczek.domain.entity.Reaction;
import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.PostRepository;
import ani.fraczek.repository.ReactionRepository;
import ani.fraczek.repository.SpadObjTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final UserService userService;

    private final ReactionRepository reactionRepository;

    private final PostRepository postRepository;

    private final SpadObjTypeRepository spadObjTypeRepository;


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

    public Optional<Reaction> addNewReactionForCurrentUser(final ReactionDTO reactionDTO) {
        User currentUser = userService.getCurrentDomainUser();
        return spadObjTypeRepository.findByType(reactionDTO.getReactionType()).flatMap(reactionType ->
                postRepository.findById(reactionDTO.getPostId()).map(post ->
                        reactionRepository.save(Reaction.builder()
                                .reactionType(reactionType)
                                .author(currentUser)
                                .post(post)
                                .content(reactionDTO.getContent())
                                .title(reactionDTO.getTitle())
                                .build())
                )
        );
    }


}
