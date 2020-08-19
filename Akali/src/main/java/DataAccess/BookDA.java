package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccessObject.BookDAO;
import Entity.Book;

public class BookDA implements BookDAO {

    private String query;
    private ResultSet rs;

    @Override
    public ArrayList<Book> getList() {
        ArrayList<Book> lBooks = new ArrayList<>();
        query = "SELECT * FROM Books;";
        try {
            DBHelper.getConnection();
            rs = DBHelper.executeQuery(query);
            while (rs.next()) {
                Book book = getBook(rs);
                lBooks.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lBooks;
    }

    @Override
    public Book getBook(int ID) throws SQLException {
        Book book = null;
        query = "SELECT * FROM Books WHERE ID = " + ID + ";";
        DBHelper.getConnection();
        rs = DBHelper.executeQuery(query);
        while (rs.next()) {
            book = getBook(rs);
        }
        DBHelper.closeConnection();
        return book;
    }

    private Book getBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setID(rs.getInt("ID"));
        book.setTitle(rs.getString("Title"));
        book.setPrice(rs.getDouble("Price"));
        book.setAuthor(rs.getString("Author"));
        book.setIssuingCompany(rs.getString("IssuingCompany"));
        book.setDateofPublication(rs.getString("DateofPublication"));
        book.setDimensions(rs.getString("Dimensions"));
        book.setCoverType(rs.getString("CoverType"));
        book.setNumberofPages(rs.getInt("NumberofPages"));
        book.setPublishingCompany(rs.getString("PublishingCompany"));
        book.setSKU(rs.getString("SKU"));
        return book;
    }
}