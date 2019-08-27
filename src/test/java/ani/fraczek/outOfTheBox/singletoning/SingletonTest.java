package ani.fraczek.outOfTheBox.singletoning;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {


    @Test
    public void singleton_createdByClassLoader_mayHave_manyInstances() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        Class<?> SingleClass = systemClassLoader.loadClass("ani.fraczek.outOfTheBox.singletoning.Single");

        Object obj = SingleClass.newInstance();
        Assert.assertTrue(obj instanceof Single);;
        Object obj2 = SingleClass.newInstance();
        Assert.assertTrue(obj2 instanceof Single);
        Assert.assertNotEquals(obj, obj2);

        Single single = (Single) obj;

//        interesting, getInstance hasn't been called while single's instance single has been initialized
        Assert.assertEquals(0l, single.getGetInstanceCounter());
        Assert.assertNotEquals(single, single.getInstance());
    }






}
