package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Action;
import fr.ensicaen.service.IGenericService;

@Transactional
public class ActionService implements IGenericService<Action> {

    private static final long serialVersionUID = -3615872587970639669L;
    private IGenericDAO<Action> dao;

	@Override
	public void add(Action user) {
		dao.create(user);
	}

	@Override
	public void update(Action user) {
		dao.update(user);
	}

	@Override
	public void delete(Action user) {
		dao.delete(user);
	}

	@Override
	public Action find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Action> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Action> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Action> dao) {
		this.dao = dao;
	}

}
