package fr.ensicaen.bean;

import fr.ensicaen.entity.Action;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Service;
import fr.ensicaen.entity.Tag;
import fr.ensicaen.service.IGenericService;
import fr.ensicaen.service.IServiceService;

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
    private IServiceService serviceService;

    @ManagedProperty("#{clientService}")
    private IGenericService<Client> clientService;

    @ManagedProperty("#{actionService}")
    private IGenericService<Action> actionService;

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

    public IGenericService<Action> getActionService() {
        return actionService;
    }

    public void setActionService(IGenericService<Action> actionService) {
        this.actionService = actionService;
    }

    public Tag getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag = selectedTag;
    }

    public IServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public void addService(Service service) {
        this.client.getServiceList().add(service);
        this.clientService.update(this.client);
        this.actionService.add(new Action(this.client, service, true));
    }


    public List<Service> getServiceList() {
        return this.serviceService.getServicesByTag(this.client, this.selectedTag);
    }
}
