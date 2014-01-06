package fr.ensicaen.entity;
import javax.persistence.*;

import java.io.Serializable;


/**
 * 
 * @author mhiri
 *
 */

@Table(name = "entreprise")

public class Entreprise extends Client implements Serializable {

	@OneToOne
	private String adresse;
	
	@OneToOne
	private String name;


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
