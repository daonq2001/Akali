package DataAccessObject;

import java.sql.SQLException;

import Entity.Order;

public interface OrderDAO {
    Boolean addOrder(Order order) throws SQLException;
    Order getOrder() throws SQLException;
}