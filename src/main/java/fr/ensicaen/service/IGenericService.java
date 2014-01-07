package fr.ensicaen.service;

import java.util.List;

public interface IGenericService<T> {

	public void add(T user);

	public void update(T user);

	public void delete(T user);

	public T find(Long id);

	public List<T> getAll();

}
