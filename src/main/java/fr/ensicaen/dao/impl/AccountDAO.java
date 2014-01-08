package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class AccountDAO implements IGenericDAO<Account> {
    private static final long serialVersionUID = -2918570853513861374L;
    private SessionFactory sessionFactory;

    @Override
    public void create(Account obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public Account find(Long id) {
        List list = sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from " + Account.class.getName()
                                + " where id_account=?").setParameter(0, id)
                .list();
        if (list != null && !list.isEmpty()) {
            return (Account) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(Account obj) {
        Account tmp = (Account) sessionFactory.getCurrentSession().merge(obj);
        sessionFactory.getCurrentSession().update(tmp);
    }

    @Override
    public void delete(Account obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Account> readAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + Account.class.getName()).list();
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
