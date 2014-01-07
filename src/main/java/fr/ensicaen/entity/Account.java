package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User: Jérémie Drouet Date: 06/01/14
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {
	private static final long serialVersionUID = 6095491421553685983L;

	@Id
	@Column(name = "id_account", nullable = false)
	private Long idAccount;

	@Column(name = "balance", nullable = false)
	private double balance;

	@ManyToOne
	@JoinColumn(name = "client", nullable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "account_type", nullable = false)
	private AccountType accountType;

	@OneToMany(mappedBy = "account")
	private List<Card> cardList;

	@OneToMany(mappedBy = "source")
	private List<Operation> debitList;

	@OneToMany(mappedBy = "destination")
	private List<Operation> creditList;

	public Account() {
	}

	public Account(Client client, AccountType accountType, double balance) {
		this.client = client;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idCompte) {
		this.idAccount = idCompte;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public List<Operation> getDebitList() {
		return debitList;
	}

	public void setDebitList(List<Operation> debitList) {
		this.debitList = debitList;
	}

	public List<Operation> getCreditList() {
		return creditList;
	}

	public void setCreditList(List<Operation> creditList) {
		this.creditList = creditList;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}
}
