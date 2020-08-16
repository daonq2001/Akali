package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessObject.OrderDAO;
import Entity.Order;

public class OrderDA implements OrderDAO {

    String query;

    @Override
    public Boolean addOrder(Order order) throws SQLException {
        Connection con = DBHelper.getConnection();
        DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 0;");
        query = "INSERT INTO Orders(CustomerID) values (?);";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, order.getCustomerID());
        Boolean b = preparedStatement.execute();
        DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 1;");
        DBHelper.closeConnection();
        return b;
    }

    @Override
    public Order getOrder() throws SQLException {
        query = "SELECT * FROM Orders ORDER BY ID DESC LIMIT 1;";
        DBHelper.getConnection();
        ResultSet rs = DBHelper.executeQuery(query);
        Order order = new Order();
        while(rs.next()){
            order.setID(rs.getInt("ID"));
            order.setCustomerID(rs.getInt("CustomerID"));
            order.setDate(rs.getTimestamp("Date"));
        }
        DBHelper.closeConnection();
        return order;
    }


    
}