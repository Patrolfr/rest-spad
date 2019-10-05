package ani.fraczek.repository;

import ani.fraczek.domain.entity.Post;
import ani.fraczek.domain.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findAllByPost(Post post);

}
