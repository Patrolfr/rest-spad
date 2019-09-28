package ani.fraczek.service;

import ani.fraczek.domain.dto.UserDTO;
import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getCurrentDomainUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByLogin(userDetails.getUsername());
    }

    public String getCurrentUserLogin() {
        return getCurrentDomainUser().getLogin();
    }

    public long getCurrentUserId() {
//        TODO throw CurrentUserNotPresent? for tests?
        return getCurrentDomainUser().getId();
    }

}
