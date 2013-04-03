package me.huzorro.simple.thread;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class ThreadLocalExample1Test {
    private ThreadLocal<Boolean> var = new ThreadLocal<Boolean>() {

        /* (non-Javadoc)
         * @see java.lang.ThreadLocal#initialValue()
         */
        @Override
        protected Boolean initialValue() {
            // TODO Auto-generated method stub
            return true;
        }
        
    };
    private CountDownLatch latch = new CountDownLatch(1);
    private static ThreadLocalExample1 t;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        t = new ThreadLocalExample1(var, latch);
    }
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
    }    
    /**
     * Test method for {@link me.huzorro.simple.thread.ThreadLocalExample1#run()}.
     */
    @Test
    public void testRun() {
        t.start();
        latch.countDown();
        assertEquals(true, t.var.get());
    }

}
