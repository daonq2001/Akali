package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccessObject.OrderDetailDAO;
import Entity.OrderDetail;

public class OrderDetailDA implements OrderDetailDAO {

    String query;

    @Override
    public Boolean addOrderDetail(OrderDetail orderDetail) throws SQLException {
        Connection con = DBHelper.getConnection();
        DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 0;");
        query = "INSERT INTO OrderDetails(BookID, OrderID, Amount) values (?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, orderDetail.getBookID());
        preparedStatement.setInt(2, orderDetail.getOrderID());
        preparedStatement.setInt(3, orderDetail.getAmount());
        Boolean b = preparedStatement.execute();
        DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 1;");
        DBHelper.closeConnection();
        return b;
    }

    @Override
    public ArrayList<OrderDetail> getOrderDetail(int OrderID) throws SQLException {
        query = "SELECT * FROM OrderDetails WHERE OrderID = " + OrderID + ";";
        DBHelper.getConnection();
        ResultSet rs = DBHelper.executeQuery(query);
        ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = new OrderDetail();
        while(rs.next()){
            orderDetail.setOrderID(rs.getInt("OrderID"));
            orderDetail.setBookID(rs.getInt("BookID"));
            orderDetail.setAmount(rs.getInt("Amount"));
            orderDetails.add(orderDetail);
        }
        DBHelper.closeConnection();
        return orderDetails;
    }
    
}