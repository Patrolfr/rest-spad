package ani.fraczek.repository;

import ani.fraczek.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    User findUserByLogin(String login);

    @Transactional
    void deleteByLogin(String login);

}
