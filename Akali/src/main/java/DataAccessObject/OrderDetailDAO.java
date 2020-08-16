package DataAccessObject;

import java.sql.SQLException;
import java.util.ArrayList;

import Entity.OrderDetail;

public interface OrderDetailDAO {
    Boolean addOrderDetail(OrderDetail orderDetail) throws SQLException;
    ArrayList<OrderDetail> getOrderDetail(int OrderID) throws SQLException;
}