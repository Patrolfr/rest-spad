package ani.fraczek.controller;


import ani.fraczek.domain.entity.Foo;
import ani.fraczek.repository.FooRepository;
import ani.fraczek.repository.SpadObjTypeRepository;
import ani.fraczek.security.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static ani.fraczek.domain.definition.Consts.NOT_DOMAIN_OBJECT;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final FooRepository fooRepository;

    private final SpadObjTypeRepository objTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @GetMapping(value = "hello")
    public String hello(HttpServletRequest request){
        logger.debug("ROLE_ADMIN: " + request.isUserInRole("ROLE_ADMIN"));
        logger.debug("ROLE_USER: " + request.isUserInRole("ROLE_USER"));
        return "Hello";
    }

    @GetMapping(value = "helloUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloUser(HttpServletRequest request){
        logger.debug("ROLE_ADMIN: " + request.isUserInRole("ROLE_ADMIN"));
        logger.debug("ROLE_USER: " + request.isUserInRole("ROLE_USER"));
        return "HelloUser";
    }

    @GetMapping(value = "helloAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloAdmin(HttpServletRequest request){
        logger.debug("ROLE_ADMIN: " + request.isUserInRole("ROLE_ADMIN"));
        logger.debug("ROLE_USER: " + request.isUserInRole("ROLE_USER"));
        objTypeRepository.findByType(NOT_DOMAIN_OBJECT)
                .ifPresent(objType ->
                        fooRepository.save(Foo.builder().objType(objType).build())
                );

        return "HelloAdmin";
    }

}
