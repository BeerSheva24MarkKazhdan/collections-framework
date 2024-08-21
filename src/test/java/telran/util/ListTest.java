package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
//Integer[] array = {3, -10, 20, 1, 10, 8, 100, 17};
public abstract class ListTest extends CollectionTest{
List<Integer> list;
@Override
void setUp(){
    super.setUp();
    list = (List<Integer>) collection;
}
@Test
    void addTest(){
        list.add(0, 10);
    assertEquals(10, list.get(0));
    assertEquals(3, list.get(1));
    assertEquals(9, list.size());

}
@Test
    void removeTest(){
        list.remove(0);
        assertEquals(-10, list.get(0));
        assertEquals(7, list.size());
    }

    @Test
    void getTest(){
assertEquals(3, list.get(0));
assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
    }

    @Test
    void indexOfTest(){
        assertEquals(0, list.indexOf(3));
        assertEquals(-1, list.indexOf(10000));

    }

    @Test
    void lastIndexOfTest(){
        list.add(2);
        list.add(3);
        assertEquals(8, list.lastIndexOf(2));
        assertEquals(9, list.lastIndexOf(3));
        assertEquals(-1, list.lastIndexOf(1000));

    }
}
