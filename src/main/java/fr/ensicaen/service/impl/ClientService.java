package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Client;
import fr.ensicaen.service.IGenericService;

@Transactional
public class ClientService implements IGenericService<Client> {

	private IGenericDAO<Client> dao;

	@Override
	public void add(Client user) {
		dao.create(user);

	}

	@Override
	public void update(Client user) {
		dao.update(user);
	}

	@Override
	public void delete(Client user) {
		dao.delete(user);
	}

	@Override
	public Client find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Client> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Client> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Client> dao) {
		this.dao = dao;
	}

}
