package ani.fraczek.service;

import ani.fraczek.domain.entity.Post;
import ani.fraczek.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getALlPostsOfUser(Long userId){
        return postRepository.findAllByPoster(userId);
    }



}
