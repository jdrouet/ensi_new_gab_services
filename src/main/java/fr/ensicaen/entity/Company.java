package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe héritant de la classe client, pour définir une entreprise
 * 
 * @author mhiri
 */
@Entity
@Table(name = "company")
public class Company extends Client implements Serializable {
	private static final long serialVersionUID = 2611603814153599254L;

	/**
	 * Adresse de l'entreprise
	 */
	@Column(name = "address")
	private String address;

	/**
	 * Nome de l'entreprise
	 */
	@Column(name = "name")
	private String name;

	public String getAddress() {
		return address;
	}

	public void setAddress(String adresse) {
		this.address = adresse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void generateP12() {
		// TODO Auto-generated method stub

	}
}
