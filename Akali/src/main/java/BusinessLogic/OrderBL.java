package BusinessLogic;

import java.sql.SQLException;

import DataAccess.OrderDA;
import Entity.Order;

public class OrderBL {
    OrderDA dal = new OrderDA();

    public Boolean addOrder(Order order) throws SQLException {
        return dal.addOrder(order);
    }

    public Order getOrder() throws SQLException {
        return dal.getOrder();
    }
}