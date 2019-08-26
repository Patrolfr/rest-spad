package ani.fraczek;


import ani.fraczek.domain.entity.Foo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final FooRepository fooRepository;

    @GetMapping(value = "hello")
    public String hello(){
        fooRepository.save(new Foo());
        return "Hello";
    }


}
