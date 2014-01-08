package fr.ensicaen.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T> extends Serializable {

	public void create(T obj);

	public T find(Long id);

	public List<T> readAll();

	public void update(T obj);

	public void delete(T obj);
}
