package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe conteneur du type de compte
 * 
 * @author mhiri
 */
@Entity
@Table(name = "account_type")
public class AccountType implements Serializable {
	private static final long serialVersionUID = 4270625962503463818L;

	/**
	 * Identifiant du type de compte
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_account_type", nullable = false)
	private Long idAccountType;

	/**
	 * Nom du type
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	/**
	 * Plafond pour ce type de compte
	 */
	@Column(name = "ceiling")
	private Double ceiling;

	/**
	 * Taux pour ce type de compte
	 */
	@Column(name = "rate")
	private double rate;

	public Long getIdAccountType() {
		return idAccountType;
	}

	public void setIdAccountType(Long idAccountDetail) {
		this.idAccountType = idAccountDetail;
	}

	public Double getCeiling() {
		return ceiling;
	}

	public void setCeiling(Double plafonds) {
		this.ceiling = plafonds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAccountType == null) ? 0 : idAccountType.hashCode());
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
		AccountType other = (AccountType) obj;
		if (idAccountType == null) {
			if (other.idAccountType != null)
				return false;
		} else if (!idAccountType.equals(other.idAccountType))
			return false;
		return true;
	}

}
