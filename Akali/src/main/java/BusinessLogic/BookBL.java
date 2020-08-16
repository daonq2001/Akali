package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.BookDA;
import Entity.Book;

public class BookBL {
    BookDA bookDAL = new BookDA();

    public ArrayList<Book> getList() {
        return bookDAL.getList();
    }

    public Book getBook(int ID) throws SQLException {
        return bookDAL.getBook(ID);
    }
}