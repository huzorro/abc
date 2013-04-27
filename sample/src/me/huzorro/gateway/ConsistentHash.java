package me.huzorro.gateway;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class ConsistentHash<T> {
    private final Collection<T> nodes;
    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    private final SortedMap<Long, T> circle = new TreeMap<Long, T>();
    /**
     * 
     * @param nodes
     */
    public ConsistentHash(Collection<T> nodes) {        
        this(Hashing.md5(), 100, nodes);
    }
    /**
     * 
     * @param hashFunction
     * @param numberOfReplicas
     * @param nodes
     */
    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
            Collection<T> nodes) {
        this.nodes = nodes;
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hashString(node.toString() + i).asLong(),
                    node);
        }
    }

    public void delete(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hashString(node.toString() + i).asLong());
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        Long hash = hashFunction.hashString(key.toString()).asLong();
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
    public Collection<T> getNodes() {
        return nodes;
    }
}
