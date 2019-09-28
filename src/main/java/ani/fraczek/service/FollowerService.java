package ani.fraczek.service;

import ani.fraczek.domain.entity.FollowerFollowee;
import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final UserService userService;

    private final FollowerRepository followerRepository;

    public void addFolloweeForCurrentUser(final String followeeLogin){
        User followee = userService.getUserByLogin(followeeLogin);
        followerRepository.save(new FollowerFollowee(userService.getCurrentUserId(), followee.getId()));
    }

    public Set<String> getCurrentUserFollowees(){
        return followerRepository.findUserFolloweesLoginsByUserId(userService.getCurrentUserId());
    }

}
