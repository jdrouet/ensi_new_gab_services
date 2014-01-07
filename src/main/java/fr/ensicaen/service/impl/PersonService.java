package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Person;
import fr.ensicaen.service.IGenericService;

@Transactional
public class PersonService implements IGenericService<Person> {

	private IGenericDAO<Person> dao;

	@Override
	public void add(Person user) {
		dao.create(user);

	}

	@Override
	public void update(Person user) {
		dao.update(user);
	}

	@Override
	public void delete(Person user) {
		dao.delete(user);
	}

	@Override
	public Person find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Person> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Person> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Person> dao) {
		this.dao = dao;
	}

}
