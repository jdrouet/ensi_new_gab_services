package fr.ensicaen.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import fr.ensicaen.dao.GenericDAO;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class GenericDAOImpl<T, PK extends Serializable> implements
		GenericDAO<T, PK> {
	private SessionFactory sessionFactory;

	private Class<T> type;

	/**
	 * @param type
	 */
	public GenericDAOImpl(Class<T> type) {
		this.type = type;
	}

	public PK create(T object) {
		return (PK) getSession().save(object);
	}

	public T find(PK id) {
		return (T) getSession().get(type, id);
	}

	public List<T> readAll() {
		return readByCriteria();
	}

	public List<T> readByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(type);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public void update(T object) {
		getSession().update(object);
	}

	public void delete(T object) {
		getSession().delete(object);
	}

	/**
	 * @return
	 */
	public Session getSession() {
		boolean allowCreate = true;
		return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
	}

	/**
	 * Injection via Spring
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
