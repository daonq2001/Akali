package DataAccessObject;

import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Book;

public interface BookDAO {
    ArrayList<Book> getList();
    Book getBook(int ID) throws SQLException;
}
