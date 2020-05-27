package ani.fraczek.playground.testrunner;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTestRunnerTest {

    private final int EXPECTED_SUCCESSFUL_TESTS = 1;

    @Retention(RUNTIME)
    @Target(ElementType.METHOD)
    @interface TestThrowsEx {
    }

    @Slf4j
    static class SimpleTestRunner {
        public static int run(Class<?> testClass) {
            int passedTests = 0;
            for (Method testMethod : testClass.getMethods()) {
                if (testMethod.isAnnotationPresent(TestThrowsEx.class)) {
                    try {
                        testMethod.invoke(null); // null cause test method is static
                        log.debug("{} failed not throwing exception.", testMethod.getName());
                    } catch (InvocationTargetException e) {
                        log.debug("\"{}\" succeed with exception: \"{}\"",
                                testMethod.getName(),
                                e.getTargetException());
                        passedTests++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return passedTests;
        }
    }

    static class SampleTestsClass {
        @TestThrowsEx
        public static void shouldThrowEx() {
            throw new RuntimeException("Test throws exception.");
//            int a = 1/0;
        }
    }

    @Test
    public void simpleTestRunner_shouldRun_SampleTestsClass_shouldThrowEx_testMethod() {
        int successfulTests = SimpleTestRunner.run(SampleTestsClass.class);
        assertThat(successfulTests).isEqualTo(EXPECTED_SUCCESSFUL_TESTS);
    }

}
