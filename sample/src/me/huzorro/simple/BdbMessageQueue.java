/**
 * 
 */
package me.huzorro.simple;

import java.util.concurrent.ConcurrentHashMap;

import me.huzorro.gateway.CmppDeliverRequestMessage;
import me.huzorro.gateway.CmppSubmitRequestMessage;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.collections.TransactionWorker;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;

/**
 * @author huzorro
 *
 */
public class BdbMessageQueue<T> {

    private static Logger log = LoggerFactory.getLogger(BdbMessageQueue.class);
    private static final String MESSAGE_STORE = "message_store";

    private Database messageDb;
    private StoredSortedMap<Long, T> messageMap;

    private TransactionRunner transactionRunner;

    //EnqueueWorker和DequeueWorker的同步对象
    private Object syncObject = new Object();

    public BdbMessageQueue(BdbEnvironment bdbEnvironment, String queueName)
            throws DatabaseException {
        try {
            // Set the Berkeley DB config for opening all stores.
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setTransactional(true);
            dbConfig.setAllowCreate(true);

            
            // Open the Berkeley DB database for the part, supplier and shipment
            // stores.  The stores are opened with no duplicate keys allowed.
            messageDb = bdbEnvironment.getEnvironment().openDatabase(null, MESSAGE_STORE + queueName, dbConfig);

            SerialBinding<Long> messageKeyBinding =
                    new SerialBinding<Long>(bdbEnvironment.getJavaCatalog(), Long.class);
            SerialBinding<Object> messageValueBinding =
                    new SerialBinding<Object>(bdbEnvironment.getJavaCatalog(), Object.class);

            messageMap = new StoredSortedMap<Long, T>(messageDb, messageKeyBinding, (EntryBinding<T>) messageValueBinding, true);

            // Create transactionRunner for the transactional operation
            transactionRunner = new TransactionRunner(bdbEnvironment.getEnvironment());
            log.info(messageMap.size() + "");
        } catch (DatabaseException dbe) {
        	dbe.printStackTrace();
        }
    }


    /**
     * 安全关闭berkeley 数据库
     */
    public void close() {
        try {
            if (messageDb != null) messageDb.close();
        } catch (DatabaseException dbe) {
        	dbe.printStackTrace();
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public void dequeue() {
        try {
            DequeueWorker worker = new DequeueWorker();
            transactionRunner.run(worker);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return ;
    }

    /**
     * 入队
     *
     * @param message
     */
    public void enqueue(T message) {
        try {
            EnqueueWorker worker = new EnqueueWorker(message);
            transactionRunner.run(worker);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    


    /**
     * 出队列事务Worker，内部类
     */
     class DequeueWorker implements TransactionWorker {


        private DequeueWorker() {
        }

        public void doWork() throws Exception {

            Long firstKey;
            T message;

//            synchronized (syncObject) {
//                //没有获得消息就不起床
//                while ((firstKey = messageMap.firstKey()) == null ||
//                        (message = (T) messageMap.get(firstKey)) == null) {
//                    syncObject.wait();
//                }
//            }
            //如果执行任务的时候，抛出了RuntimeException,消息不会从队列中删除。BDB事务也会回滚。
//            task.handelMessage(message);
            log.info("size:" + messageMap.size());
            firstKey = messageMap.firstKey();
            messageMap.remove(firstKey);
            
            log.info(String.format("DequeueWorker dequeue %1$s. ", firstKey));
//            if (log.isDebugEnabled()) {
//                log.debug(String.format("DequeueWorker dequeue %1$s. ", message));
//            }
        }

    }

    /**
     * 入队列事务Worker，内部类
     */
     class EnqueueWorker implements TransactionWorker {
        private T message;

        private EnqueueWorker(T message) {
            this.message = message;
        }

        public void doWork() throws Exception {
            synchronized (syncObject) {
                Long lastKey = messageMap.lastKey();
                lastKey = (lastKey == null) ? 1L : ++lastKey;
                messageMap.put(lastKey, message);
                log.info(String.format("EnqueueWorker enqueue %1$s %2$s. ", lastKey ,message));
                syncObject.notify();
            }
            
        }
    }
}

