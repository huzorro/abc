package test.me.huzorro.simple;

import static org.junit.Assert.*;

import me.huzorro.simple.Example1;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class example1Test {
    Example1 ex1 = new Example1();
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link me.huzorro.simple.Example1#add(int[])}.
     */
    @Test
    public void testAdd() {
        assertEquals(6, ex1.add(1, 2, 3));
    }

    /**
     * Test method for {@link me.huzorro.simple.Example1#div(int, int)}.
     */
    @Test
    public void testDiv() {
        assertEquals(3, ex1.div(6, 2));
    }

}
