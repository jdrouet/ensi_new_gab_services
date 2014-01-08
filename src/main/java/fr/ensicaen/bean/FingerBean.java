package fr.ensicaen.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
	public static final String PAGE = "/pages/finger.xhtml";

	@ManagedProperty("#{homeBean}")
	private HomeBean homeBean;

	private String result;

	public HomeBean getHomeBean() {
		return homeBean;
	}

	public void setHomeBean(HomeBean homeBean) {
		this.homeBean = homeBean;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
