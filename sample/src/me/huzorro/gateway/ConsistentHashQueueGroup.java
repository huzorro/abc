package me.huzorro.gateway;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;

import com.google.common.hash.HashFunction;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 * @param <E>
 */
public class ConsistentHashQueueGroup<T extends BlockingQueue<E>, E> extends ConsistentHash<T> {

    /**
     * @param nodes
     */
    public ConsistentHashQueueGroup(Collection<T> nodes) {
        super(nodes);
    }

    /**
     * @param hashFunction
     * @param numberOfReplicas
     * @param nodes
     */
    public ConsistentHashQueueGroup(HashFunction hashFunction,
            int numberOfReplicas, Collection<T> nodes) {
        super(hashFunction, numberOfReplicas, nodes);
    }
    /**
     * 
     * @param message
     * @throws InterruptedException
     */
    public void put(E message) throws InterruptedException {
        this.get(message).put(message);
    }
    
    /**
     *  
     * @return E
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {
        return this.get(System.nanoTime()).take();
    }
    /**
     * 
     * @param message
     * @return boolean
     */
    public boolean remove(E message) {
        return this.get(message).remove(message);
    }
}
