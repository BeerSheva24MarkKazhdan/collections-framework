package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")

public class TreeSet<T> implements Set<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T obj) {
            this.obj = obj;
        }
    }
    public Node<T> findMin(Node<T> node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> nextNode = findMin(root);
        Node<T> currNode = null;

        @Override
        public boolean hasNext() {
            return nextNode!=null;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            currNode=nextNode;
            
            if(nextNode.right!=null){
                nextNode = findMin(nextNode.right);
            } else {
                while (nextNode.parent != null && compareTreeSetNodes(nextNode, nextNode.parent) >= 0) {
                    nextNode = nextNode.parent;
                }
                nextNode=nextNode.parent;
            }
            return currNode.obj;
        }

        @Override
        public void remove() {
            if (currNode == null) {
                throw new IllegalStateException();
            }
            boolean ifRemoved = TreeSet.this.remove(currNode.obj);
            if (!ifRemoved) {
                throw new IllegalStateException();
            }
            currNode = null;
        }

        

        public int compareTreeSetNodes(Node<T> node, Node<T> parentNode){
            return comparator.compare(node.obj, parentNode.obj);

        }
    }

    private Node<T> root;
    private Comparator<T> comparator;
    int size;
    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    } 
    
    public TreeSet() {
        this((Comparator<T>)Comparator.naturalOrder());
    }
    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            if(root == null) {
                addRoot(node);
            } else {
                addAfterParent(node);
            }
            size++;

        }
        return res;
    }

    private void addAfterParent(Node<T> node) {
        Node<T> parent = getParent(node.obj);
        if(comparator.compare(node.obj, parent.obj) > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
    }

    private void addRoot(Node<T> node) {
        root = node;
    }

    @Override
    public boolean remove(T pattern) {
        Node<T> nodeToRemove = getNode(pattern);
        if (nodeToRemove == null) {
            return false;
        }
        
        
        if (nodeToRemove.left==null && nodeToRemove.right==null){
            replaceNode(nodeToRemove, null);
        }
        
        else if (nodeToRemove.left != null && nodeToRemove.right == null) {
            replaceNode(nodeToRemove, nodeToRemove.left);
        } 
        
        else if (nodeToRemove.left == null && nodeToRemove.right != null) {
            replaceNode(nodeToRemove, nodeToRemove.right);
        }
        
        else {
            Node<T> minLeftNode = findMin(nodeToRemove.right);
            nodeToRemove.obj = minLeftNode.obj;
            replaceNode(minLeftNode, minLeftNode.right);
            
        }
        size--;
        return  true;
    }

    private void replaceNode(Node<T> node, Node<T> newNode) {
        if (node.parent == null) {
            root = newNode;
        } else if (node == node.parent.left) {
            node.parent.left = newNode;
        } else {
            node.parent.right = newNode;
        }
        
        if (newNode != null) {
            newNode.parent = node.parent;
        }
    }


    @Override
    public int size() {
    return size;
}

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public boolean contains(T pattern) {
        boolean res = false;
        Iterator<T> iterator = iterator();
        while(iterator.hasNext() && !res){
            if(pattern.equals(iterator.next())){
                res = true;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }

    @Override
    public T get(Object pattern) {
        Node<T> result = getNode((T) pattern);
        return result == null ? null : result.obj;
    }
    private Node<T> getParentOrNode(T pattern) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while(current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : current;
    }
    private Node<T> getNode(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        if(res != null) {
            int compRes = comparator.compare(pattern, res.obj);
            res = compRes == 0 ? res : null;
        }
        
        return res;

    }
    private Node<T> getParent(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        int compRes = comparator.compare(pattern, res.obj);
        return compRes == 0 ? null : res;

    }
}