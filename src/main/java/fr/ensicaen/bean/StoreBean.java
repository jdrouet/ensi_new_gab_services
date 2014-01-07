package fr.ensicaen.bean;

import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Service;
import fr.ensicaen.entity.Tag;
import fr.ensicaen.service.IGenericService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class StoreBean implements Serializable {
    private static final long serialVersionUID = 1980070445064631124L;

    @ManagedProperty("#{serviceService}")
    private IGenericService<Service> serviceService;

    @ManagedProperty("#{clientService}")
    private IGenericService<Client> clientService;

    @ManagedProperty("#{homeBean.client}")
    private Client client;

    private Tag selectedTag;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public IGenericService<Client> getClientService() {
        return clientService;
    }

    public void setClientService(IGenericService<Client> clientService) {
        this.clientService = clientService;
    }

    public Tag getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag = selectedTag;
    }

    public IGenericService<Service> getServiceService() {
        return serviceService;
    }

    public void setServiceService(IGenericService<Service> serviceService) {
        this.serviceService = serviceService;
    }

    public void addService(Service service) {
        this.client.getServiceList().add(service);
        this.clientService.update(this.client);
    }


    public List<Service> getServiceList() {
        if (this.selectedTag != null) {
            // return Collections.emptyList();
        } else {
            // return this.serviceService.getAll();
        }
        return this.serviceService.getAll();
    }
}
