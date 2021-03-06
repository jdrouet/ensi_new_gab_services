package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Person;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
@Transactional
public class PersonDAO implements IGenericDAO<Person> {
	private static final long serialVersionUID = 3757905726142171015L;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Person obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	@Override
	public Person find(Long id) {
		List list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from " + Person.class.getName() + " where id_person=?")
				.setParameter(0, id).list();
		if (list != null && !list.isEmpty())
			return (Person) list.get(0);
		return null;
	}

	@Override
	public void update(Person obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void delete(Person obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<Person> readAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + Person.class.getName()).list();
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
