package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
protected Collection<Integer> collection;
Integer[] array = {3, -10, 20, 1, 10, 8, 100, 17};
void setUp(){
Arrays.stream(array).forEach(collection::add);
}

@Test
void addTest(){
    assertTrue(collection.add(200));
    assertTrue(collection.add(17));
    assertEquals(array.length + 2, collection.size());
}
@Test
void sizeTest(){
    assertEquals(array.length, collection.size());
}

@Test
void removeTest(){
    assertTrue(collection.remove(100));
    assertEquals(array.length -1, collection.size());
}

@Test
void isEmptyTest(){
assertFalse(collection.isEmpty());
}

@Test
void containsTest(){
assertTrue(collection.contains(10));
assertFalse(collection.contains(1000));
}

@Test
void streamTest(){
    setUp();

    Integer[] result = (Integer[]) collection.stream()
    .filter(n -> n != null && n % 2 == 0)
    .toArray(Integer[]::new);

    Integer[] expected = {-10, 20, 10, 8, 100};
    assertArrayEquals(expected, result);
}

@Test
void parallelStreamTest(){
    setUp();
    Integer[] result = (Integer[]) collection.parallelStream()
    .filter(n -> n != null && n % 2 == 0)
    .toArray(Integer[]::new);

    Integer[] expected = {-10, 20, 10, 8, 100};
    assertArrayEquals(expected, result);
}
}
