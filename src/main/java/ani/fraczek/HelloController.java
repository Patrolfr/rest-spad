package ani.fraczek;


import ani.fraczek.domain.entity.Foo;
import ani.fraczek.security.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final FooRepository fooRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @GetMapping(value = "hello")
    public String hello(){
        fooRepository.save(new Foo());
        return "Hello";
    }

    @GetMapping(value = "helloUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloUser(HttpServletRequest request){
        logger.debug("ROLE_ADMIN: " + request.isUserInRole("ROLE_ADMIN"));
        logger.debug("ROLE_USER: " + request.isUserInRole("ROLE_USER"));
        fooRepository.save(new Foo());
        return "HelloUser";
    }

    @GetMapping(value = "helloAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloAdmin(HttpServletRequest request){
        logger.debug("ROLE_ADMIN: " + request.isUserInRole("ROLE_ADMIN"));
        logger.debug("ROLE_USER: " + request.isUserInRole("ROLE_USER"));
        fooRepository.save(new Foo());
        return "HelloAdmin";
    }

}
