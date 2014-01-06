package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author mhiri
 *
 */
@Table(name = "carte")
public class Carte extends Compte implements Serializable{

	@Id
	private Long id_card; 
	
	@OneToOne
	private int pan;
	
	

	public Long getId_card() {
		return id_card;
	}

	public void setId_card(Long id_card) {
		this.id_card = id_card;
	}

	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}
	
}
