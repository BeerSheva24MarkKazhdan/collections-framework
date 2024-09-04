package telran.util;
@SuppressWarnings("unchecked")
public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> set;
    protected abstract Set<K> getEmptyKeySet();
    @Override
    public V get(Object key) {
        
        Entry<K, V> pattern = new Entry<>((K)key, null);
       Entry<K,V> entry = set.get(pattern);
       V res = null;
       if (entry != null) {
        res = entry.getValue();
       }
       return res;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        if (containsKey(key)){
            oldValue = get(key);
            set.remove(new Entry<K,V>(key, oldValue));
        }
        set.add(new Entry<K,V>(key, value));
        return oldValue;
    }

    @Override
    public boolean containsKey(Object key) {
        Entry<K,V> pattern = new Entry<> ((K)key, null);
        return set.contains(pattern);
    }

    @Override
    public boolean containsValue(Object value) {
        Entry<K, V> pattern = new Entry<>(null, (V) value);
        return value != null && set.contains(pattern);
}

    @Override
    public Set<K> keySet() {
        Set<K> keySet = getEmptyKeySet();
        for (Entry<K, V> entry : set) {
            keySet.add(entry.getKey()); 
        }
        return keySet;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
       return set;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();

        for (Entry<K, V> entry : set) {
            values.add(entry.getValue());

        
    }
    return values;
}

    @Override
    public int size() {
       return set.size();
    }

    @Override
    public boolean isEmpty() {
       return set.isEmpty();
    }
}
