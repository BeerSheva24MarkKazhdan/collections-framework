package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public abstract class AbstractMapTest {
    Integer[] keys = {1, -1};
    Map<Integer, Integer> map;
    void setUp() {
    
    }
    abstract <T> void runTest(T [] expected, T [] actual);
    @Test
    void testPutAndGet() {
        map.put(1, 100);
        map.put(-1, 200);
        
        assertEquals(100, map.get(1));
        assertEquals(200, map.get(-1));
    }

    @Test
    void testContainsKey() {
        map.put(1, 100);
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(-1));
    }

   /*  @Test
    void testContainsValue() {
        map.put(1, 100);
        assertTrue(map.containsValue(100));
        assertFalse(map.containsValue(200));
    }*/
    @Test
    void testKeySet() {
        map.put(1, 100);
        map.put(-1, 200);

        Set<Integer> keys = map.keySet();
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(-1));
    }
    @Test
    void testIsEmpty(){
        assertTrue(map.isEmpty());
        map.put(1, 100);
        assertFalse(map.isEmpty());
    }
    @Test
    void testSize(){
        map.put(1, 100);
        assertEquals(1, map.size());
    }
}
