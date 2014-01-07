package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Card;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class CardDAO implements IGenericDAO<Card> {
	private SessionFactory sessionFactory;

	@Override
	public void create(Card obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public Card find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Account.class.getName() + " where id_card=?")
				.setParameter(0, id).list();
		if (list != null && !list.isEmpty())
			return (Card) list.get(0);
		return null;
	}

	@Override
	public void update(Card obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Card obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Card> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Card.class.getName()).list();
	}

	/**
	 * Injection via Spring
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
