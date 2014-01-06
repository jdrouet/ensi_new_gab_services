package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author mhiri
 *
 */
@Table(name = "detailsCompte")

public class DetailCompte implements Serializable {

	public int getPlafonds() {
		return plafonds;
	}

	public void setPlafonds(int plafonds) {
		this.plafonds = plafonds;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToOne
	private int plafonds;
	
	@OneToOne
	private float solde;
	
	@OneToOne
	private String type;
}
