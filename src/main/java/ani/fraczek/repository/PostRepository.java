package ani.fraczek.repository;

import ani.fraczek.domain.entity.Post;
import ani.fraczek.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPosterId(Long posterId);

    List<Post> findAllByPoster(User poster);

    @Transactional
    void deleteAllByPoster(User poster);

    Optional<Post> findByIdAndPoster(long id, User poster);

    @Query(value = "SELECT * FROM post p " +
            "WHERE p.poster_id IN (SELECT ff.followee_id FROM follower_followee ff where ff.follower_id = ?1) " +
            "ORDER BY p.created_date DESC",
            nativeQuery = true)
    Set<Post> getAllPostsOfFolloweesByUserId(long userId);

}
