package fr.ensicaen.bean.service.p2p;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.ensicaen.bean.AbstractBean;
import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.entity.Account;
import fr.ensicaen.service.IGenericService;

/**
 * User: Jérémie Drouet Date: 09/01/14
 */
@ManagedBean
@SessionScoped
public class P2PBean extends AbstractBean {
	private static final long serialVersionUID = 6055540180053992038L;

	@ManagedProperty("#{accountService}")
	private IGenericService<Account> accountService;

	@ManagedProperty("#{homeBean}")
	private HomeBean homeBean;

	private Account senderAccount;
	private Account recipientAccount;
	private String amount;

	public Account getRecipientAccount() {
		return recipientAccount;
	}

	public void setRecipientAccount(Account recipientAccount) {
		this.recipientAccount = recipientAccount;
	}

	public String getRecipientText() {
		if (this.recipientAccount == null) {
			return "";
		} else {
			return String.format("%s - %s - %d", this.recipientAccount
					.getClient().getName(), this.recipientAccount
					.getAccountType().getName(), this.recipientAccount
					.getIdAccount());
		}
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public IGenericService<Account> getAccountService() {
		return accountService;
	}

	public void setAccountService(IGenericService<Account> accountService) {
		this.accountService = accountService;
	}

	public HomeBean getHomeBean() {
		return homeBean;
	}

	public void setHomeBean(HomeBean homeBean) {
		this.homeBean = homeBean;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * Fausse lecture de tag, on prend un compte aléatoire dans la base de
	 * données et on fait une transaction
	 */
	public void readTag() {
		System.out.println("== READ TAG ==");
		List<Account> lst = this.accountService.getAll();
		System.out.println(lst);
		if (this.senderAccount == null) {
			this.recipientAccount = null;
		} else {
			lst.removeAll(this.getHomeBean().getClient().getAccountList());
			if (lst.size() > 0) {
				Collections.shuffle(lst);
				this.recipientAccount = lst.get(0);
			} else {
				this.recipientAccount = null;
			}
		}
	}

}
