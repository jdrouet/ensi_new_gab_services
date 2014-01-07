package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.*;


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
}
