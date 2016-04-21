package services;

import domain.Book;
import java.util.ArrayList;

import data_access.BookDao;

public class LibraryService {

	private BookDao bookDao;

	public LibraryService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void registerBook(String isbn, String title) {
		bookDao.insertBook(isbn, title);

	}

	public void listRegisteredBooks() {
		ArrayList<Book> books = bookDao.listAll();
		for (Book book : books) {
			System.out.println(book);
		}

	}

	public void updateBookRegistrations(String isbn, String title) {
		bookDao.updateBook(isbn, title);

	}

	public void unregisterBooks(String isbn) {
		bookDao.deleteBook(isbn);

	}

}
