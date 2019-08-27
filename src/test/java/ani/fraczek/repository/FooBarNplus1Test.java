package ani.fraczek.repository;


import ani.fraczek.FooRepository;
import ani.fraczek.domain.entity.Foo;
import org.junit.Assert;
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

        Foo foo = fooRepository.save(new Foo());
        Assert.assertEquals(1l, fooRepository.findAll().size());

    }

}
