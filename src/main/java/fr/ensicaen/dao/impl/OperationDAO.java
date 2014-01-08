package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Operation;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class OperationDAO implements IGenericDAO<Operation> {
    private static final long serialVersionUID = 3384270473692720501L;
    private SessionFactory sessionFactory;

	@Override
	public void create(Operation obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public Operation find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Operation.class.getName()
								+ " where id_operation=?").setParameter(0, id)
				.list();
		if (list != null && !list.isEmpty())
			return (Operation) list.get(0);
		return null;
	}

	@Override
	public void update(Operation obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Operation obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Operation> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Operation.class.getName()).list();
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
