package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Tag;
import fr.ensicaen.service.IGenericService;

@Transactional
public class TagService implements IGenericService<Tag> {

	private IGenericDAO<Tag> dao;

	@Override
	public void add(Tag user) {
		dao.create(user);

	}

	@Override
	public void update(Tag user) {
		dao.update(user);
	}

	@Override
	public void delete(Tag user) {
		dao.delete(user);
	}

	@Override
	public Tag find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Tag> getAll() {
		return dao.readAll();
	}

	public IGenericDAO<Tag> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Tag> dao) {
		this.dao = dao;
	}

}
