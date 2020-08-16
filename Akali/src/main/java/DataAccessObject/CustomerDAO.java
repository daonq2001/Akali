package DataAccessObject;

import java.sql.SQLException;

import Entity.Customer;

public interface CustomerDAO {
    Customer getCustomer(String Email) throws SQLException;
    Customer getbyID(int ID) throws SQLException;
}