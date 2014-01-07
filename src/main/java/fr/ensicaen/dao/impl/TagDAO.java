package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Organization;
import fr.ensicaen.entity.Tag;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class TagDAO implements IGenericDAO<Tag> {
	private SessionFactory sessionFactory;

	@Override
	public void create(Tag obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public Tag find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Tag.class.getName() + " where id_tag=?")
				.setParameter(0, id).list();
		if (list != null && !list.isEmpty())
			return (Tag) list.get(0);
		return null;
	}

	@Override
	public void update(Tag obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Tag obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Tag> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Tag.class.getName()).list();
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
