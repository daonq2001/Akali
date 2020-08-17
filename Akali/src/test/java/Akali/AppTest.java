package Akali;

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
    public void shouldAnswerWithTrue() throws SQLException
    {
        assertTrue(customerBL.Login("ngoquangdao@gmail.com", "ngoquangdao"));
    }
}
