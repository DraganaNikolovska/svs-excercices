package com.data_access;

import java.util.List;

import com.domain.Publication;

public interface PublicationDao {

	public List<Publication> listAll();
	public Publication getPublication(Integer id);
}
