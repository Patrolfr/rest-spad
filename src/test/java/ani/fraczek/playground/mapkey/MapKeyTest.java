package ani.fraczek.playground.mapkey;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class MapKeyTest {


    @Test
    public void test_creatingTwoSameInstancesOfString_with_intern_and_stringPool(){
        StringBuilder stringBuilder = new StringBuilder();

        String bar = "bar";
        String bar1 = "bar";

//        Same objects, bar1 value object taken from String Pool
        Assert.assertEquals(bar, bar1);
        Assert.assertSame(bar, bar1);


        String bar3 = stringBuilder.append("bar").toString();
        Assert.assertEquals(bar, bar3);
//        String builder by default does not check String existence in string pool and creates extra object
        Assert.assertNotSame(bar, bar3);


        stringBuilder.setLength(0); //clear stringBuilder

        String bar4 = stringBuilder.append("bar").toString().intern();
        Assert.assertEquals(bar, bar4);
//        Make String builder check existence of value object in string pool by intern() invocation
        Assert.assertSame(bar, bar4);
    }


    @Test
    public void test_map_getByKey_uses_EqualsContract_NotSameContract(){
        StringBuilder stringBuilder = new StringBuilder();

        String key1 = "key1";
        String key1Build = stringBuilder.append("key1").toString();

        Assert.assertEquals(key1, key1Build);
        Assert.assertNotSame(key1, key1Build);

        Map<String, String> map = Collections.singletonMap(key1, "Value 1");

//        Key contract is (key==null ? k==null : key.equals(k))
        Assert.assertTrue(map.containsKey(key1));
        Assert.assertTrue(map.containsKey(key1Build));
    }

    @Test
    public void test_string_hashCode(){
        StringBuilder stringBuilder = new StringBuilder();

        String str = "str";
        String str1 = stringBuilder.append("str").toString();

        Assert.assertNotSame(str, str1);
        Assert.assertEquals(str, str1);
        Assert.assertEquals(str.hashCode(), str1.hashCode());
    }




}
