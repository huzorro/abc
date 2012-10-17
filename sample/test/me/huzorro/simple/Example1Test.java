package me.huzorro.simple;

import static org.junit.Assert.*;

import me.huzorro.simple.Example1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class Example1Test {
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
    @Test(timeout = 1000)
    public void testAdd() {
        assertEquals(6, ex1.add(1, 2, 3));
    }

    /**
     * Test method for {@link me.huzorro.simple.Example1#div(int, int)}.
     */
    @Test(expected = ArithmeticException.class)
    public void testDiv() {
        assertEquals(3, ex1.div(6, 0));
    }
    @Test
    public void testDiv1() {
        assertEquals(3, ex1.div(6, 0));
    }
    @Ignore
    @Test
    public void testMultiply() {
        assertEquals(6, ex1.multiply(2, 3));
    }
}
