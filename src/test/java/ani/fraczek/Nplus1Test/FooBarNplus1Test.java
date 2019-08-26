package ani.fraczek.Nplus1Test;


import ani.fraczek.FooRepository;
import ani.fraczek.domain.entity.Foo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FooBarNplus1Test {


    @Autowired
    private FooRepository fooRepository;



    @Test
    public void givenListOfElements_Test(){



        fooRepository.save(new Foo());



    }


}
