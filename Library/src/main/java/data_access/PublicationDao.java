package data_access;

import java.util.List;

import domain.Publication;

public interface PublicationDao {

	public List<Publication> listAll();
}
