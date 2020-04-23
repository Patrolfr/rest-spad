package ani.fraczek.playground.enums;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class EnumsTests {

    public enum SeatState {
        OCCUPIED, FREE, RESERVED, BROKEN, VIP
    }

    private final static EnumSet<SeatState> allowedSeatStates = EnumSet.of(SeatState.FREE, SeatState.VIP);

    private final static Set<SeatState> unmodifeableAllowedSeatStates = Collections.unmodifiableSet(allowedSeatStates);

    @Test
    public void test() {
        assertTrue(allowedSeatStates.contains(SeatState.FREE));

    }



}
