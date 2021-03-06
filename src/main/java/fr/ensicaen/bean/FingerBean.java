package fr.ensicaen.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Client;

/**
 * User: Jérémie Drouet Date: 08/01/14
 */
@ManagedBean
@SessionScoped
public class FingerBean extends AbstractBean {
	private static final long serialVersionUID = 4819179251339653633L;
	public static final String PAGE = "/pages/finger/finger.xhtml";

	@ManagedProperty("#{homeBean}")
	private HomeBean homeBean;

	private Command command;
	private int result;
	private String after;

	public HomeBean getHomeBean() {
		return homeBean;
	}

	public void setHomeBean(HomeBean homeBean) {
		this.homeBean = homeBean;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Client getClient() {
		return this.homeBean.getClient();
	}

	public Account getAccount() {
		return this.homeBean.getAccount();
	}

	public Card getCard() {
		return this.homeBean.getCard();
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public void initialize(Command command, String after) {
		this.setCommand(command);
		this.setAfter(after);
	}

	public void proceed() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (this.result == 0) {
			this.getCommand().onSuccess();
			homeBean.setInfo("Authentification réussie - Action effectuée");
			facesContext
					.getApplication()
					.getNavigationHandler()
					.handleNavigation(facesContext, null,
							after + "?faces-redirect=false");
		} else {
			this.getCommand().onFail();
			facesContext.addMessage(null, new FacesMessage(
					"Authentificcation raté", "Action non effectuée"));
		}
	}

	public static abstract class Command implements Serializable {
		private static final long serialVersionUID = 4979412193763108182L;

		public abstract void onSuccess();

		public abstract void onFail();

	}

}
