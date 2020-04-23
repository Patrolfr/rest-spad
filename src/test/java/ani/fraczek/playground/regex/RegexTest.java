package ani.fraczek.playground.regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class RegexTest {

    private final Pattern oneDigitPattern = Pattern.compile("\\d");
    private final Pattern onlyDigitsPattern = Pattern.compile("\\d+");
    private final Pattern onlyDigitsWith123Prefix = Pattern.compile("123\\d+");
    private final Pattern onlyDigitsWith123PrefixV2 = Pattern.compile("123[0-9]");
    private final Pattern only1to3and7to9DigitsWith123Prefix = Pattern.compile("123[1-3,7-9]");
    private final Pattern only1to3and7DigitsWith123Prefix = Pattern.compile("123[1-3,7]");
    private final Pattern only5and7DigitsWith123Prefix = Pattern.compile("123[5,7]");

    @Test
    public void test1() {
        Assert.assertTrue(oneDigitPattern.matcher("3").matches());
        Assert.assertFalse(oneDigitPattern.matcher("12").matches());
    }

    @Test
    public void test2() {
        Assert.assertTrue(onlyDigitsPattern.matcher("3").matches());
        Assert.assertTrue(onlyDigitsPattern.matcher("123").matches());
        Assert.assertFalse(onlyDigitsPattern.matcher("12.3").matches());
        Assert.assertFalse(onlyDigitsPattern.matcher("123a").matches());
        Assert.assertFalse(onlyDigitsPattern.matcher("a").matches());
    }

    @Test
    public void test3() {
        Assert.assertTrue(onlyDigitsWith123Prefix.matcher("1233").matches());
        Assert.assertTrue(onlyDigitsWith123Prefix.matcher("1234").matches());
        Assert.assertFalse(onlyDigitsWith123Prefix.matcher("123").matches());
        Assert.assertFalse(onlyDigitsWith123Prefix.matcher("123a").matches());
        Assert.assertFalse(onlyDigitsWith123Prefix.matcher("3215").matches());
        Assert.assertFalse(onlyDigitsWith123Prefix.matcher("3215123").matches());
    }

    @Test
    public void test4() {
        Assert.assertTrue(onlyDigitsWith123PrefixV2.matcher("1233").matches());
        Assert.assertTrue(onlyDigitsWith123PrefixV2.matcher("1234").matches());
        Assert.assertFalse(onlyDigitsWith123PrefixV2.matcher("123").matches());
        Assert.assertFalse(onlyDigitsWith123PrefixV2.matcher("123a").matches());
        Assert.assertFalse(onlyDigitsWith123PrefixV2.matcher("3215").matches());
    }

    @Test
    public void test5() {
        Assert.assertTrue(only1to3and7to9DigitsWith123Prefix.matcher("1231").matches());
        Assert.assertTrue(only1to3and7to9DigitsWith123Prefix.matcher("1232").matches());
        Assert.assertTrue(only1to3and7to9DigitsWith123Prefix.matcher("1233").matches());
        Assert.assertFalse(only1to3and7to9DigitsWith123Prefix.matcher("1234").matches());
        Assert.assertFalse(only1to3and7to9DigitsWith123Prefix.matcher("1235").matches());
        Assert.assertFalse(only1to3and7to9DigitsWith123Prefix.matcher("1236").matches());
        Assert.assertTrue(only1to3and7to9DigitsWith123Prefix.matcher("1237").matches());
        Assert.assertTrue(only1to3and7to9DigitsWith123Prefix.matcher("1238").matches());
        Assert.assertTrue(only1to3and7to9DigitsWith123Prefix.matcher("1239").matches());
    }

    @Test
    public void test6() {
        Assert.assertTrue(only1to3and7DigitsWith123Prefix.matcher("1231").matches());
        Assert.assertTrue(only1to3and7DigitsWith123Prefix.matcher("1232").matches());
        Assert.assertTrue(only1to3and7DigitsWith123Prefix.matcher("1233").matches());
        Assert.assertTrue(only1to3and7DigitsWith123Prefix.matcher("1237").matches());

        Assert.assertFalse(only1to3and7DigitsWith123Prefix.matcher("1234").matches());
        Assert.assertFalse(only1to3and7DigitsWith123Prefix.matcher("1235").matches());
        Assert.assertFalse(only1to3and7DigitsWith123Prefix.matcher("1236").matches());
        Assert.assertFalse(only1to3and7DigitsWith123Prefix.matcher("1238").matches());

        Assert.assertFalse(only1to3and7DigitsWith123Prefix.matcher("123a").matches());
        Assert.assertFalse(only1to3and7DigitsWith123Prefix.matcher("12311").matches());
    }

    @Test
    public void test7() {
        Assert.assertTrue(only5and7DigitsWith123Prefix.matcher("1235").matches());
        Assert.assertTrue(only5and7DigitsWith123Prefix.matcher("1237").matches());
        Assert.assertTrue(only5and7DigitsWith123Prefix.matcher("123,").matches());
        Assert.assertFalse(only5and7DigitsWith123Prefix.matcher("1231").matches());
        Assert.assertFalse(only5and7DigitsWith123Prefix.matcher("1236").matches());
        Assert.assertFalse(only5and7DigitsWith123Prefix.matcher("1238").matches());
        Assert.assertFalse(only5and7DigitsWith123Prefix.matcher("123a").matches());
        Assert.assertFalse(only5and7DigitsWith123Prefix.matcher("123").matches());
    }

}
