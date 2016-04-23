package data_access;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import domain.Entity;
import domain.Magazine;

public interface Dao {

	public void insert(Entity entity);

	public void update(Entity entity);

	public void delete(Entity entity);

	public List<Entity> listAll();
}
