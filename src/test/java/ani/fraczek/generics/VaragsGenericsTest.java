package ani.fraczek.generics;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VaragsGenericsTest {


    @Test(expected = ClassCastException.class)
    public void dangerousArguments_shouldThrow_ClassCastEx() {

        @SuppressWarnings("unchecked") // suppressing this cast is a huge mistake
                String s = dangerousArguments(List.of("asd", "bce"));
    }

    @Test(expected = ClassCastException.class)
    public void dangerousArgumentsUnraveled_shouldThrow_ClassCastEx() {
        List<String> stringList = List.of("asd", "bce");
        List<List<String>> listOfListsOfStrings = List.of(stringList);
        String s = dangerousArgumentsUnraveled((List<String>[]) listOfListsOfStrings.toArray());
    }

    static String dangerousArguments(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList;
        String s = stringLists[0].get(0);
        return s;
    }

    static String dangerousArgumentsUnraveled(List<String>[] stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList; // Heap pollution
        String s = stringLists[0].get(0); // ClassCastException
        return s;
    }

    static void safeArguments(List<List<String>> stringLists) {
        List<Integer> intList = List.of(42);
//        Object[] objects = stringLists; // cannot cast non-reifiable type to Object[]
//        objects[0] = intList;
//        String s = stringLists[0].get(0);
    }


    @Test(expected = ClassCastException.class)
    public void pickTwo_shouldThrow_unexpectedClassCastEx_causebBy_compileTimeTypeInference() {
        String[] attributes = pickTwo("Good", "Fast", "Cheap");
    }

    static <T> T[] pickTwo(T a, T b, T c) { switch(ThreadLocalRandom.current().nextInt(3)) {
        case 0: return toArray(a, b);
        case 1: return toArray(a, c);
        case 2: return toArray(b, c);
    }
        throw new AssertionError(); // Can't get here
    }

    static <T> T[] toArray(T... args) {
        return args; // never expose reference to varargs array!
    }

}
