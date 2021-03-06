package fr.ensicaen.bean;

import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Action;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Service;
import fr.ensicaen.service.IGenericService;
import fr.ensicaen.service.IServiceService;

/**
 * User: Jérémie Drouet Date: 07/01/14
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

	private String info;

	private String clearPin;
	private Card card;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				info, info));

		this.info = info;
	}

	public String getClearPin() {
		return clearPin;
	}

	public void setClearPin(String clearPin) {
		this.clearPin = clearPin;
	}

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
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Successful", service.getName() + " removed"));
	}

	public String disconnect() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		session.invalidate();
		return "/pages/disconnect.xhtml";
	}
}
