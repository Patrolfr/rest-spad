package ani.fraczek.playground;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class DiverseTypeContainerTest {

    DiverseTypeContainer diverseTypeContainer = new DiverseTypeContainer();

    public static class DiverseTypeContainer {

        private Map<Class<?>, Object> container = new HashMap<>();

        public <T> void put(Class<T> type, T value) {
            container.put(Objects.requireNonNull(type), type.cast(value));
        }

        public <T> T get(Class<T> type) {
            return type.cast(container.get(type));
        }
    }

    @Test
    public void put_shouldStore_valuesOfType_String_Integer_Object() {
        diverseTypeContainer.put(String.class, "some_str");
        diverseTypeContainer.put(Integer.class, 123);
        Object obj = new Object();
        diverseTypeContainer.put(Object.class, obj);

        String retrievedStr = diverseTypeContainer.get(String.class);
        assertThat(retrievedStr).isSameAs("some_str");

        Integer retrievedInteger = diverseTypeContainer.get(Integer.class);
        assertThat(retrievedInteger).isSameAs(123);

        Object o = diverseTypeContainer.get(Object.class);
        assertThat(o).isSameAs(obj);
    }
}
