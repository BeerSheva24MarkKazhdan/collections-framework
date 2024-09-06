package telran.util;
import java.util.Iterator;
import telran.util.LinkedList.Node;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> implements Set<T> {
    private LinkedList<T> list = new LinkedList<>();
    HashMap<T, Node<T>> map = new HashMap<>();

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);
            res = true;
        }

        return res;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        Node<T> node = map.get(pattern);
    if (node == null) {
        res = false;
    } else {
        list.removeNode(node);
        map.remove(pattern);  
    }
    return res;
}  

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(T pattern) {
        return map.containsKey(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Iterator<T> listIterator = list.iterator();
    
            @Override
            public boolean hasNext() {
                return listIterator.hasNext();
            }
    
            @Override
            public T next() {
                return listIterator.next(); 
            }
        };
    }

    @Override
    public T get(Object pattern) {
        Node<T> node = map.get(pattern);
    if (node == null) {
        return null; 
    }
    return node.obj; 
}
}
