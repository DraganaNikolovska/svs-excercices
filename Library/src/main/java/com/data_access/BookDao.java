package com.data_access;

import com.domain.Book;

public interface BookDao extends Dao{

	public Book findByIsbn(String isbn);
	
	public void updateBookTitle(String isbn, String title);

	public Book findById(Integer id);
	
}
