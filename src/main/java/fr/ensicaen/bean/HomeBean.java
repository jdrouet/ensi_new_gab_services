package fr.ensicaen.bean;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Service;
import fr.ensicaen.service.IGenericService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class HomeBean implements Serializable {
    private static final long serialVersionUID = -4523859625680715721L;
    public static final String PAGE = "/pages/home.xhtml";

    @ManagedProperty("#{clientService}")
    private IGenericService<Client> clientService;

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

    public IGenericService<Client> getClientService() {
        return clientService;
    }

    public void setClientService(IGenericService<Client> clientService) {
        this.clientService = clientService;
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

    public String getServicePage(Service service) {
        return "/pages/services/" + service.getPath() + "/index.xhtml";
    }

    public void removeService(Service service) {
        this.getClient().getServiceList().remove(service);
        this.clientService.update(this.getClient());
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
}
