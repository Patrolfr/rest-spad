package ani.fraczek.repository;

import ani.fraczek.domain.entity.FollowerFollowee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FollowerRepository extends JpaRepository<FollowerFollowee, Long> {

    @Query(value = "SELECT u.login " +
            "FROM follower_followee ff " +
            "       JOIN spad_user u on ff.followee_id = u.id " +
            "WHERE ff.follower_id = ?1",
    nativeQuery = true)
    Set<String> findUserFolloweesLoginsByUserId(final long userId);

}
