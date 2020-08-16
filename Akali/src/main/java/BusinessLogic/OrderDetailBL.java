package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.OrderDetailDA;
import Entity.OrderDetail;

public class OrderDetailBL {
    OrderDetailDA detailDAL = new OrderDetailDA();

    public Boolean addOrderDetail(OrderDetail orderDetail) throws SQLException {
        return detailDAL.addOrderDetail(orderDetail);
    }

    public ArrayList<OrderDetail> getOrderDetail(int OrderID) throws SQLException {
        return detailDAL.getOrderDetail(OrderID);
    }
}