package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * 
 * @author mhiri
 *
 */
@Entity
@Table(name = "account_detail")
public class AccountDetail implements Serializable {
    private static final long serialVersionUID = 4270625962503463818L;

    @Id
    @Column(name = "id_account_detail", nullable = false)
    private Long idAccountDetail;

    @Column(name = "ceiling")
    private int ceiling;

    @Column(name = "balance")
    private float balance;

    @Column(name = "type")
    private String type;


    public int getCeiling() {
		return ceiling;
	}

	public void setCeiling(int plafonds) {
		this.ceiling = plafonds;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float solde) {
		this.balance = solde;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
