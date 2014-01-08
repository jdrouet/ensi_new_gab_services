package fr.ensicaen.service.impl;

import java.util.*;

import fr.ensicaen.service.IServiceService;
import org.springframework.transaction.annotation.Transactional;

import fr.ensicaen.dao.IGenericDAO;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Service;
import fr.ensicaen.entity.Tag;
import fr.ensicaen.service.IGenericService;

@Transactional
public class ServiceService implements IServiceService {

    private static final long serialVersionUID = -1188064387928596879L;
    private IGenericDAO<Service> dao;

    @Override
    public void add(Service user) {
        dao.create(user);

    }

    @Override
    public void update(Service user) {
        dao.update(user);
    }

    @Override
    public void delete(Service user) {
        dao.delete(user);
    }

    @Override
    public Service find(Long id) {
        return dao.find(id);
    }

    @Override
    public List<Service> getAll() {
        return dao.readAll();
    }

    public IGenericDAO<Service> getDao() {
        return dao;
    }

    public void setDao(IGenericDAO<Service> dao) {
        this.dao = dao;
    }

    /**
     * Retrieve all services concern by the tag (without existing service in
     * client profil)
     *
     * @param tag
     * @return
     */
    public List<Service> getServicesByTag(Client c, Tag tag) {
        List<Service> tmp = new ArrayList<>();
        List<Service> ret = getAll();
        ret.removeAll(c.getServiceList());
        if (tag != null) {
            for (Service srv : ret) {
                boolean b = false;
                if (srv.getTagList() != null) {
                    for (Tag t : srv.getTagList()) {
                        b |= t.getIdTag().equals(tag.getIdTag());
                    }
                }
                if (b) {
                    tmp.add(srv);
                }
            }
        }
        return ret;
    }

    /**
     * counts the number of occurrences of tag
     *
     * @return
     */
    public Map<Tag, Integer> getOccTag() {
        Map<Tag, Integer> ret = new HashMap<>();
        List<Service> services = getAll();
        Iterator<Service> it = services.iterator();
        while (it.hasNext()) {
            Service s = it.next();
            for (Tag t : s.getTagList()) {
                Integer cpt = ret.get(t);
                if (cpt != null) {
                    ret.put(t, cpt + 1);
                } else {
                    ret.put(t, 1);
                }
            }
        }
        return ret;
    }

}
