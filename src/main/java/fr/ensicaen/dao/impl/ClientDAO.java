package fr.ensicaen.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Client;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class ClientDAO implements IGenericDAO<Client> {
    private SessionFactory sessionFactory;

    @Override
    public void create(Client obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public Client find(Long id) {
        List list = sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from " + Client.class.getName()
                                + " where id_client=?").setParameter(0, id)
                .list();
        if (list != null && !list.isEmpty())
            return (Client) list.get(0);
        return null;
    }

    @Override
    public void update(Client obj) {
        Client tmp = (Client) sessionFactory.getCurrentSession().merge(obj);
        sessionFactory.getCurrentSession().update(tmp);
        sessionFactory.getCurrentSession().refresh(obj);
    }

    @Override
    public void delete(Client obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Client> readAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + Client.class.getName()).list();
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
