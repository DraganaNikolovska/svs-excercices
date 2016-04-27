package com.data_access;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import com.domain.Entity;
import com.domain.Magazine;

public interface Dao<E> {

	public void insert(Entity entity);

	public void delete(Integer id);

	public List<Entity> listAll();

	public Entity findById(Integer id);
}
