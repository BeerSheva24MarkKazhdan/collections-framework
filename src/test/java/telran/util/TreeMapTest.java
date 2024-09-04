package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(expectedSorted);
        T[] actualSorted = Arrays.copyOf(actual, actual.length);
        Arrays.sort(actualSorted);    
        assertArrayEquals(expectedSorted, actualSorted);
        assertEquals(expected.length, actual.length);
    }
    
    @BeforeEach
    @Override
    void setUp() {
        map = new TreeMap<>();
        super.setUp();
    }
}
