package telran.util;
import java.util.*;
import java.util.function.Predicate;


@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    List<T>[] hashTable;
    float factor;
    int size;
    private class HashSetIterator implements Iterator<T> {
        //Hint:
        Iterator<T> currentIterator = null;
        Iterator<T> prevIterator = null;
        int indexIterator = 0;

        @Override
        public boolean hasNext() {
            if (currentIterator != null && currentIterator.hasNext()) {
                return true;
            }
            while (indexIterator < hashTable.length) {
                List<T> list = hashTable[indexIterator++];
                if (list != null && !list.isEmpty()) {
                    currentIterator = list.iterator();
                    if (currentIterator.hasNext()) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prevIterator=currentIterator;
            return currentIterator.next();
        }
        @Override
        public void remove() {
            if (prevIterator == null) {
                throw new IllegalStateException("No element to remove");
            }
            prevIterator.remove();
    }
}

    public HashSet(int hashTableLength, float factor) {
        hashTable = new List[hashTableLength];
        this.factor = factor;
    }

    public HashSet() {
        this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            if (size >= hashTable.length * factor) {
                hashTableReallocation();
            }

            addObjInHashTable(obj, hashTable);
            size++;
        }
        return res;

    }

    private void addObjInHashTable(T obj, List<T>[] table) {
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list == null) {
            list = new ArrayList<>(3);
            table[index] = list; 
        }
        list.add(obj);
    }

    private int getIndex(T obj, int length) {
        int hashCode = obj.hashCode();
        return Math.abs(hashCode % length);
    }

    private void hashTableReallocation() {
       List<T> []tempTable = new List[hashTable.length * 2];
       for(List<T> list: hashTable) {
        if(list != null) {
            list.forEach(obj -> addObjInHashTable(obj, tempTable));
            list.clear(); //??? for testing if it doesn't work remove this statement
        }
       }
       hashTable = tempTable;

    }

    @Override
    public boolean remove(T pattern) {
        int index = getIndex(pattern, hashTable.length);
    List<T> list = hashTable[index];
    if (list != null) {
        boolean removed = list.remove(pattern);
        if (removed) {
            size--;
            return true;
        }
    }
    return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
       return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        int index = getIndex(pattern, hashTable.length);
        List<T> list = hashTable[index];
        return list != null && list.contains(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashSetIterator();
    }

    @Override
    public T get(Object pattern) {
        int index = getIndex((T) pattern, hashTable.length);
        List<T> list = hashTable[index];
        if (list != null) {
            for (T element : list) {
                if (element.equals(pattern)) {
                    return element;
                }
            }
        }
        return null;
    }
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                hashTable[i].clear();
            }
        }
        size = 0;
    }
    
    public boolean removeIf(Predicate<T> predicate) {
        boolean removed = false;
        for (int i = 0; i < hashTable.length; i++) {
            List<T> list = hashTable[i];
            if (list != null) {
                Iterator<T> iterator = list.iterator();
                while (iterator.hasNext()) {
                    T element = iterator.next();
                    if (predicate.test(element)) {
                        iterator.remove();
                        size--;
                        removed = true;
                    }
                }
            }
        }
        return removed;
}
}
