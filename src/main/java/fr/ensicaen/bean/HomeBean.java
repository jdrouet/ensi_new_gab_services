package fr.ensicaen.bean;

import fr.ensicaen.entity.*;
import fr.ensicaen.service.IGenericService;
import fr.ensicaen.service.IServiceService;
import fr.ensicaen.service.impl.ServiceService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class HomeBean extends AbstractBean {
    private static final long serialVersionUID = -4523859625680715721L;
    public static final String PAGE = "/pages/home.xhtml";

    @ManagedProperty("#{clientService}")
    private IGenericService<Client> clientService;

    @ManagedProperty("#{actionService}")
    private IGenericService<Action> actionService;
    
    @ManagedProperty("#{serviceService}")
    private IServiceService serviceService;

    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Account getAccount() {
        return this.card.getAccount();
    }

    public Client getClient() {
        return this.getAccount().getClient();
    }

    public IGenericService<Action> getActionService() {
        return actionService;
    }

    public void setActionService(IGenericService<Action> actionService) {
        this.actionService = actionService;
    }

    public IGenericService<Client> getClientService() {
        return clientService;
    }

    public void setClientService(IGenericService<Client> clientService) {
        this.clientService = clientService;
    }

    public IServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public List<Service> getSelectedServiceList() {
        if (this.card == null) {
            return Collections.emptyList();
        } else if (this.card.getAccount() == null) {
            return Collections.emptyList();
        } else if (this.card.getAccount().getClient() == null) {
            return Collections.emptyList();
        } else {
            return this.card.getAccount().getClient().getServiceList();
        }
    }
    
    public List<Service> getNonRemovableServicesList() {
        return this.serviceService.getNonRemovableServices();
    }

    public String getServicePage(Service service) {
        return "/pages/services/" + service.getPath() + "/index.xhtml";
    }

    public void removeService(Service service) {
        this.getClient().getServiceList().remove(service);
        this.clientService.update(this.getClient());
        this.actionService.add(new Action(this.getClient(), service, false));
        //
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successful",
                        service.getName() + " removed"
                )
        );
    }

    public String disconnect() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "/pages/disconnect.xhtml";
    }
}
