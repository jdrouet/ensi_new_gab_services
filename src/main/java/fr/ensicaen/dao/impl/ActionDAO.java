package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Action;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class ActionDAO implements IGenericDAO<Action> {
    private SessionFactory sessionFactory;

    @Override
    public void create(Action obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public Action find(Long id) {
        List list = sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from " + Action.class.getName()
                                + " where id_action=?").setParameter(0, id)
                .list();
        if (list != null && !list.isEmpty())
            return (Action) list.get(0);
        return null;
    }

    @Override
    public void update(Action obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(Action obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Action> readAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + Action.class.getName()).list();
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
