package ani.fraczek.security;

import ani.fraczek.domain.CurrentUser;
import ani.fraczek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user by username: {}", username);

        return userRepository.findByLogin(username)
                .map(user -> CurrentUser.builder()
                        .username(user.getLogin())
                        .password(user.getEncryptedPassword())
                        .build())
                .orElseThrow(() -> new RuntimeException("UserNotFound"));
    }

}
