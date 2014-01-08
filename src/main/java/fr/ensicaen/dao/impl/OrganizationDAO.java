package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Organization;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class OrganizationDAO implements IGenericDAO<Organization> {
    private static final long serialVersionUID = -2049396457747392602L;
    private SessionFactory sessionFactory;

	@Override
	public void create(Organization obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	@Override
	public Organization find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Organization.class.getName()
								+ " where id_organization=?")
				.setParameter(0, id).list();
		if (list != null && !list.isEmpty())
			return (Organization) list.get(0);
		return null;
	}

	@Override
	public void update(Organization obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Organization obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Organization> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Organization.class.getName()).list();
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
