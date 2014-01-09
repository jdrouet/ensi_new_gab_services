package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Service;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class ServiceDAO implements IGenericDAO<Service> {
	private static final long serialVersionUID = -5452974058955362841L;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Service obj) {
		sessionFactory.getCurrentSession().merge(obj);
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public Service find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Service.class.getName()
								+ " where id_service=?").setParameter(0, id)
				.list();
		if (list != null && !list.isEmpty())
			return (Service) list.get(0);
		return null;
	}

	@Override
	public void update(Service obj) {
		sessionFactory.getCurrentSession().merge(obj);
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Service obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Service> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Service.class.getName()).list();
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
