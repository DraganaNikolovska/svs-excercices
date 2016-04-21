package data_access;

import java.awt.print.Book;
import java.util.ArrayList;

/**
 * BookDao interface is defined for accessing book table, independently from the
 * connection type.
 * <br><br>
 * The class that implements this interface should define it`s own methods for
 * accessing the book data ( insertBook, updateBook, delete, listAll) </br>
 */
public interface BookDao {

	public void insertBook(String isbn, String title);

	public void updateBook(String isbn, String title);

	public void deleteBook(String isbn);

	public ArrayList<domain.Book> listAll();

}
