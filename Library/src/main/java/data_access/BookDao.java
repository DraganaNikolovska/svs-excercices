package data_access;

import domain.Book;

public interface BookDao extends Dao{

	public Book findByIsbn(String isbn);
	
}
