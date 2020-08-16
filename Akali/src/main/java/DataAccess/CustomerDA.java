package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessObject.CustomerDAO;
import Entity.Customer;

public class CustomerDA implements CustomerDAO {
    String query;
    ResultSet rs;

    @Override
    public Customer getCustomer(String Email) throws SQLException {
        query = "SELECT * FROM Customers WHERE Email = '" + Email + "';";
        DBHelper.getConnection();
        rs = DBHelper.executeQuery(query);
        Customer c = null;

        if (rs.next() != false) {
            c = new Customer();
            c.setID(rs.getInt("ID"));
            c.setEmail(rs.getString("Email"));
            c.setPassword(rs.getString("Password"));
            c.setName(rs.getString("Name"));
            c.setAddress(rs.getString("Address"));
            c.setPhone(rs.getString("Phone"));
        }
        return c;
    }

    @Override
    public Customer getbyID(int ID) throws SQLException {
        query = "SELECT * FROM Customers WHERE ID = " + ID + ";";
        DBHelper.getConnection();
        rs = DBHelper.executeQuery(query);
        Customer c = new Customer();
        while (rs.next()) {
            c.setID(rs.getInt("ID"));
            c.setEmail(rs.getString("Email"));
            c.setPassword(rs.getString("Password"));
            c.setName(rs.getString("Name"));
            c.setPhone(rs.getString("Phone"));
            c.setAddress(rs.getString("Address"));
        }
        return c;
    }

}