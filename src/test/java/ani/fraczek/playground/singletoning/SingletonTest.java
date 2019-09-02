package ani.fraczek.playground.singletoning;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

public class SingletonTest {


    @Test
    @Ignore //add @Order to make it work properly..
    @DirtiesContext
    public void singleton_createdByClassLoader_mayHave_manyInstances() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        Class<?> SingleClass = systemClassLoader.loadClass("ani.fraczek.playground.singletoning.Single");

        Object obj = SingleClass.newInstance();
        Assert.assertTrue(obj instanceof Single);;
        Object obj2 = SingleClass.newInstance();
        Assert.assertTrue(obj2 instanceof Single);
        Assert.assertNotEquals(obj, obj2);

        Single single = (Single) obj;

//        interesting, getInstance hasn't been called while single's instance single has been initialized
        Assert.assertEquals(0L, single.getGetInstanceCounter());
        Assert.assertNotEquals(single, single.getInstance());
    }

    @Test
    public void singleton_singleInstance_test(){

        Assert.assertEquals(0L, Single.getGetInstanceCounter());

        Single instance = Single.getInstance();
        Assert.assertEquals(1L, Single.getGetInstanceCounter());

        Single instance2 = Single.getInstance();
        Assert.assertEquals(2L, Single.getGetInstanceCounter());

        Assert.assertEquals(instance, instance2);
        Assert.assertEquals(instance.hashCode(), instance2.hashCode());
        Assert.assertTrue(instance == instance2);
    }

}
