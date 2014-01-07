package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.AccountType;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class AccountTypeDAO implements IGenericDAO<AccountType> {
	private SessionFactory sessionFactory;

	@Override
	public void create(AccountType obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public AccountType find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + AccountType.class.getName()
								+ " where id_account_type=?")
				.setParameter(0, id).list();
		if (list != null && !list.isEmpty())
			return (AccountType) list.get(0);
		return null;
	}

	@Override
	public void update(AccountType obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(AccountType obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<AccountType> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + AccountType.class.getName()).list();
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
