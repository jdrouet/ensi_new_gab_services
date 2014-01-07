package fr.ensicaen.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Card;
import fr.ensicaen.service.IGenericService;

@Transactional
public class CardService implements IGenericService<Card> {

	private IGenericDAO<Card> dao;

	@Override
	public void add(Card user) {
		dao.create(user);

	}

	@Override
	public void update(Card user) {
		dao.update(user);
	}

	@Override
	public void delete(Card user) {
		dao.delete(user);
	}

	@Override
	public Card find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<Card> findAll() {
		return dao.readAll();
	}

	public IGenericDAO<Card> getDao() {
		return dao;
	}

	public void setDao(IGenericDAO<Card> dao) {
		this.dao = dao;
	}

}
