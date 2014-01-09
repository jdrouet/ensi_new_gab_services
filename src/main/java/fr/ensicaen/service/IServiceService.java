package fr.ensicaen.service;

import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Service;
import fr.ensicaen.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
public interface IServiceService extends IGenericService<Service> {

    public List<Service> getServicesByTag(Client c, Tag tag);
    
    public List<Service> getNonRemovableServices();

    public Map<Tag, Integer> getOccTag();

}
