package test.me.huzorro.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import me.huzorro.simple.Example1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
@RunWith(Parameterized.class)
public class Example1Test1 {
    private static Example1 ex1 = new Example1();
    private int param1;
    private int param2;
    private int result;
    
    /**
     * @param paramters
     * @param result
     */
    public Example1Test1(int param1, int param2, int result) {
        this.param1 = param1;
        this.param2 = param2;
        this.result = result;
    }
    @Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][]{
                {2, 3, 6}, {3, 5, 15}
        });
    }
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link me.huzorro.simple.Example1#add(int[])}.
     */
    @Ignore
    @Test
    public void testAdd() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link me.huzorro.simple.Example1#div(int, int)}.
     */
    @Ignore
    @Test
    public void testDiv() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link me.huzorro.simple.Example1#multiply(int, int)}.
     */
    @Test
    public void testMultiply() {
        assertEquals(result, ex1.multiply(param1, param2));
    }

}
