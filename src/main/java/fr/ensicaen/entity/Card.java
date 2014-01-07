package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author mhiri
 *
 */
@Entity
@Table(name = "carte")
public class Card extends Account implements Serializable{
    private static final long serialVersionUID = -7336446907414202184L;

    @Id
    @Column(name = "id_card")
	private Long idCard;
	
	@Column(name = "pan")
	private int pan;
	
	

	public Long getIdCard() {
		return idCard;
	}

	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}

	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}
	
}
