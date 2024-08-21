package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
private Object[] array;
private int size;
public ArrayList(int capacity){
    array = new Object[capacity];
}
public ArrayList(){
    this(DEFAULT_CAPACITY);
}
    @Override
    public boolean add(T obj) {
        if(size == array.length){
            reallocate();
        }
        array[size++] = obj;
        return true;
    }

    private void reallocate() {
    array = Arrays.copyOf(array, array.length * 2);    
    }

    @Override
    public boolean remove(T pattern) {
        int index = indexOf(pattern);
        
        if (index == -1){
            return false;
            }

        while (index < size - 1){
            array[index] = array[index + 1];
            index++;
        }
        array[--size] = null;
            return true;
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
       return indexOf(pattern) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
       return new Iterator<T>() {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current <= size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[current++];
        }
    };
    }

    @Override
    public void add(int index, T obj) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            reallocate();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        int i = 0;
        while (i < array.length){
            if (pattern != null && pattern.equals(array[i])){
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T pattern) {
        int i = size - 1;
        while (i >= 0){
            if (pattern != null && pattern.equals(array[i])){
                return i;
            }
            i--;
        }
        return -1;
    }
}
