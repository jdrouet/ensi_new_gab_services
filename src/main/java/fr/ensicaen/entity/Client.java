package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Classe conteneur du client générique
 * 
 * @author mhiri
 * @date 06/01/2014
 */
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client implements Serializable {
	private static final long serialVersionUID = -4995265848265187571L;

	/**
	 * Identifiant du client dans la base de données
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_client", nullable = false)
	private Long idClient;

	@Column(name = "email")
	private String email;

	/**
	 * Liste des comptes liés au client
	 */
	@OneToMany(mappedBy = "client", cascade = { CascadeType.ALL })
	@LazyCollection(value = LazyCollectionOption.FALSE)
	private List<Account> accountList;

	/**
	 * Liste des services activés au client
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@JoinTable(name = "client_service", joinColumns = { @JoinColumn(name = "id_client") }, inverseJoinColumns = { @JoinColumn(name = "id_service") })
	private List<Service> serviceList;

	/**
	 * Liste des actions d'activation et de desactivation des services par le
	 * client
	 */
	@OneToMany(mappedBy = "client", cascade = { CascadeType.ALL })
	@LazyCollection(value = LazyCollectionOption.FALSE)
	private List<Action> actionList;

	/**
	 * P12 du client (certificat utilisateur signe par la CA de la banque)
	 */
	@Column(name = "p12", columnDefinition = "text")
	private String p12;

	public Client() {
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public abstract String getName();

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public String getP12() {
		return p12;
	}

	public void setP12(String p12) {
		this.p12 = p12;
	}

	public abstract void generateP12();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idClient == null) ? 0 : idClient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		return true;
	}

}
