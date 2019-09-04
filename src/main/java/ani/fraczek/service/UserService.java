package ani.fraczek.service;

import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> retrieveAll(){
        return userRepository.findAll();
    }

}
