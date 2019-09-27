package ani.fraczek.service;

import ani.fraczek.domain.dto.PostDTO;
import ani.fraczek.domain.entity.Post;
import ani.fraczek.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    public List<PostDTO> getAllPostsOfUser(String login) {
        return postRepository.findAllByPoster(userService.getUserByLogin(login))
                .stream().map(PostDTO::ofPost)
                .collect(Collectors.toList());
    }

    public Post createPostForCurrentUser(final PostDTO postDTO) {
        return postRepository.save(Post.ofPostDTOAndUser(postDTO, userService.getCurrentDomainUser()));
    }

    public Optional<Post> deleteCurrentUserPost(long postId) {
        return postRepository.findByIdAndPoster(postId, userService.getCurrentDomainUser());
    }




}
