package com.data_access;

import com.domain.Book;

public interface BookDao extends Dao{

	public Book findByIsbn(String isbn);
	
}
