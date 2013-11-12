/**
 * 
 */
package me.huzorro.gateway;

import java.io.File;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * {@linkplain BlockingQueue blocking queue} 
 * <br>基于{@code bdb-je map} 实现的持久化队列
 * <br>缺省使用自增的{@link Long}作为 {@code key}
 * <br>如果需要使用自定义的{@code key}
 * <br>为了保证队列的{@code fifo}特性
 * <br>例如使用{@link String}需要保证{@code key}的排序符合{@code fifo}特性
 * <br>使用其他对象请实现{@link Comparable}或者{@link Comparator}保证排序符合{@code fifo}特性
 * <br>缺省的持久化安全保证应用挂掉后的数据安全 不保证系统挂掉后的事务完整性
 * <br>{@link StoredSortedMap}
 * @author huzorro(huzorro@gmail.com)
 * @param <K> the type of map key
 * @param <E> the type of queue elements
 * 			
 *
 */
public class BdbQueueMap<K, E> extends AbstractQueue<E> implements BlockingQueue<E> {
	final ReentrantLock lock;
	private final Condition notEmpty;
	private final QueueEnvironment<K, E> queueEnvironment;
	private final StoredSortedMap<K, E> queueMap;
	
	public BdbQueueMap(String pathHome, String queueName) {
		lock = new ReentrantLock();
		notEmpty = lock.newCondition();
		queueEnvironment = new QueueEnvironment<K, E>()
				.buildEnvironment(pathHome).buildStoredClassCatalog()
				.buildQueueMap(queueName);
		queueMap = queueEnvironment.queueMap;
	}
	
	@Override
	public E poll() {
		lock.lock();
		try {
			return extract();
		} finally {
			lock.unlock();
		}		
	}
	
	public E get(K k) {
		return queueMap.get(k);
	}
	
	E extract() {
		K firstKey;
		return (firstKey = queueMap.firstKey()) == null ? null : queueMap
				.remove(firstKey);
	}
	
	@Override
	public E peek() {
		//TODO
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean offer(E e) {
		lock.lock();
		try {
			Long lastKey = (Long) queueMap.lastKey();
			lastKey = (lastKey == null) ? 1L : ++lastKey;
			insert((K) lastKey, e);
			return true;
		} finally {
			lock.unlock();
		}
	}
	
	public boolean offer(K k, E e) {
		lock.lock();
		try {
			insert(k, e);
			return true;
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void put(E e) throws InterruptedException {
		lock.lockInterruptibly();		
		try {
			Long lastKey = (Long) queueMap.lastKey();
			lastKey = (lastKey == null) ? 1L : ++lastKey;
			insert((K) lastKey, e);
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 使用自定义的{@code key} 插入元素{@code e}到队列
	 * @see #insert(Object, Object)
	 * @param k
	 * @param e
	 * @throws InterruptedException
	 */
	public void put(K k, E e) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			insert(k, e);
		} finally {
			lock.unlock();
		}
	}
	
	void insert(K k, E e) {
		queueMap.put(k, e);
		notEmpty.signal();
	}
	
	@Override
	public boolean offer(E e, long timeout, TimeUnit unit)
			throws InterruptedException {
		return offer(e);
	}
	
	public boolean offer(K k, E e, long timeout, TimeUnit unit) {
		return offer(k, e);
	}
	
	@Override
	public E take() throws InterruptedException {
		lock.lockInterruptibly();
		K firstKey;
		try {
			while((firstKey = queueMap.firstKey()) == null)
				notEmpty.await();
			return queueMap.remove(firstKey);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		return poll();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean remove(Object e) {
		lock.lock();
		try {
			return queueMap.values().remove((E) e);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 删除指定队列中的元素
	 * <br>{@code if (k != null && e != null)}删除指定{@code key}和元素{@code e}的对应项
	 * <br>{@code if (k != null && e == null)}删除指定{@code key}的元素
	 * <br>{@code if (k == null && e != null)}删除指定元素{@code e}
	 * @param k
	 * @param e
	 * @return
	 */
	public boolean remove(K k, E e) {
		lock.lock();
		try {
			if(k != null && e != null) 
				return queueMap.remove(k, e);
			if(k != null && e == null)
				return queueMap.remove(k) != null;
			if(k == null && e != null)
				return queueMap.values().remove(e);
			return false;
		} finally {
			lock.unlock();
		}
	}
	@Override
	public Iterator<E> iterator() {
		lock.lock();
		try {
			return queueMap.values().iterator();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int size() {
		lock.lock();
		try {
			return queueMap.size();
		} finally {
			lock.unlock();
		}
		
	}
	/**
	 * {@inheritDoc}
	 * <p>返回一个缺省值{@link Integer#MAX_VALUE}
	 * <p>持久化存储，实际容量要大于缺省值{@link Integer#MAX_VALUE}
	 */
	@Override
	public int remainingCapacity() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		lock.lock();
		try {
			int n = 0;
			int max = queueMap.size();
			while(n < max) {
				c.add(extract());
				++n;
			}
			return n;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		lock.lock();
		try {
			int n = 0;
			int max = (maxElements > queueMap.size()) ? queueMap.size() : maxElements;
			while(n < max) {
				c.add(extract());
				++n;
			}
			return n;
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 清理log
	 * @see QueueEnvironment#cleanLog()
	 * @see com.sleepycat.je.Environment#cleanLog()
	 */
	public void clearLog() {
		lock.lock();
		try {
			queueEnvironment.clearLog();
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 清理log
	 * <br>关闭{@link Environment}
	 * <br>关闭{@link Database}
	 * @see QueueEnvironment#close()
	 * @see com.sleepycat.je.Environment#cleanLog()
	 * @see com.sleepycat.je.Environment#close()
	 */
	public void close() {
		lock.lock();
		try {
			queueEnvironment.close();
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 初始化{@code bdb je}环境
	 * @author huzorro(huzorro@gmail.com)
	 *
	 * @param <K>
	 * @param <E>
	 */
	static final class QueueEnvironment<K, E> {
		private Environment environment;
		private DatabaseConfig dbConfig;
		private Database classCatalogDB;
		private Database queueDB;
		private StoredClassCatalog storedClassCatalog;
		private StoredSortedMap<K, E> queueMap;
		
		public QueueEnvironment<K, E> buildEnvironment(String pathHome) {
			File home = new File(pathHome);
			EnvironmentConfig environmentConfig = new EnvironmentConfig();
			environmentConfig.setAllowCreate(true);
			environment = new Environment(home, environmentConfig);
			dbConfig = new DatabaseConfig();
			dbConfig.setAllowCreate(true);
			dbConfig.setTransactional(true);
			return this;
		}
		public QueueEnvironment<K, E> buildStoredClassCatalog() {
			return buildStoredClassCatalog("classCatalog");
		}
		public QueueEnvironment<K, E> buildStoredClassCatalog(String Name) {			
			classCatalogDB = environment.openDatabase(null, Name, dbConfig);
			storedClassCatalog = new StoredClassCatalog(classCatalogDB);
			return this;
		}

		@SuppressWarnings("unchecked")
		public QueueEnvironment<K, E> buildQueueMap(String queueName) {
			SerialBinding<Object> messageKeyBinding = new SerialBinding<Object>(
					storedClassCatalog, Object.class);
			SerialBinding<Object> messageValueBinding = new SerialBinding<Object>(
					storedClassCatalog, Object.class);
			queueDB = environment.openDatabase(null, queueName, dbConfig);
			queueMap = new StoredSortedMap<K, E>(queueDB,
					(EntryBinding<K>) messageKeyBinding,
					(EntryBinding<E>) messageValueBinding, true);
			return this;
		}
		public void clearLog() {
			environment.cleanLog();
		}
		public void close() {
			environment.cleanLog();
			classCatalogDB.close();
			queueDB.close();
			environment.close();
		}		
	}
	

}
