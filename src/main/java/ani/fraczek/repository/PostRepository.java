package ani.fraczek.repository;

import ani.fraczek.domain.entity.Post;
import ani.fraczek.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPosterId(Long posterId);

    List<Post> findAllByPoster(User poster);

    @Transactional
    void deleteAllByPoster(User poster);

    Optional<Post> findByIdAndPoster(long id, User poster);

}
