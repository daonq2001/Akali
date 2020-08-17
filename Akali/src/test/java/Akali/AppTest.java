package Akali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.CustomerBL;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    CustomerBL customerBL = new CustomerBL();

    @Test
    public void testLogin1() throws SQLException
    {
        assertTrue(customerBL.Login("ngoquangdao@gmail.com", "ngoquangdao"));
    }

    @Test
    public void testLogin2() throws SQLException
    {
        assertEquals(true, customerBL.Login("asdnasdjads", "asdnasdjads"));
    }

}