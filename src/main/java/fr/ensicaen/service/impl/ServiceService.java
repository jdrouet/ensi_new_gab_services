package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Service;
import fr.ensicaen.service.IGenericService;

@Transactional
public class ServiceService implements IGenericService<Service> {

	private IGenericDAO<Service> dao;

	@Override
	public void add(Service user) {
		dao.create(user);

	}

	@Override
	public void update(Service user) {
		dao.update(user);
	}

	@Override
	public void delete(Service user) {
		dao.delete(user);
	}

	@Override
	public Service find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Service> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Service> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Service> dao) {
		this.dao = dao;
	}

}
