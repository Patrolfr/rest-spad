package ani.fraczek.repository;


import ani.fraczek.FooRepository;
import ani.fraczek.domain.entity.Foo;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
