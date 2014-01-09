package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Company;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class CompanyDAO implements IGenericDAO<Company> {
	private static final long serialVersionUID = -3388768971936036197L;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Company obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public Company find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Company.class.getName()
								+ " where id_company=?").setParameter(0, id)
				.list();
		if (list != null && !list.isEmpty())
			return (Company) list.get(0);
		return null;
	}

	@Override
	public void update(Company obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Company obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Company> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Company.class.getName()).list();
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
