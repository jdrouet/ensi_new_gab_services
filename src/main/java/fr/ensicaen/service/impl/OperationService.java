package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IGenericService;

@Transactional
public class OperationService implements IGenericService<Operation> {

    private static final long serialVersionUID = -3514081166506754784L;
    private IGenericDAO<Operation> dao;

	@Override
	public void add(Operation user) {
		dao.create(user);

	}

	@Override
	public void update(Operation user) {
		dao.update(user);
	}

	@Override
	public void delete(Operation user) {
		dao.delete(user);
	}

	@Override
	public Operation find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Operation> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Operation> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Operation> dao) {
		this.dao = dao;
	}

}
