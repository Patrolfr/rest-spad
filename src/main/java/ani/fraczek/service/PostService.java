package ani.fraczek.service;

import ani.fraczek.domain.dto.PostDTO;
import ani.fraczek.domain.entity.Post;
import ani.fraczek.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    public List<PostDTO> getALlPostsOfUser(Long userId){
        return postRepository.findAllByPosterId(userId)
                .stream().map(PostDTO::ofPost)
                .collect(Collectors.toList());
    }

    public Post createPost(PostDTO postDTO){
        Post savedPost = postRepository.save(Post.ofPostDTOAndUser(postDTO, userService.getCurrentDomainUser()));
        return savedPost;
    }




}
