package ani.fraczek;


import ani.fraczek.domain.entity.Foo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ApplicationContextTest {

    @Autowired
    private FooRepository fooRepository;

    @Test
    public void contextLoads(){
        List<Foo> foos = fooRepository.findAll();
        Assert.assertEquals(0l, foos.size());
    }

}
