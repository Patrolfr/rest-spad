package ani.fraczek.service;

import ani.fraczek.domain.dto.UserDTO;
import ani.fraczek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> retrieveAll(){
        return userRepository.findAll().stream().map(UserDTO::ofUser).collect(Collectors.toList());
    }

}
