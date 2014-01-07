package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * 
 * @author mhiri
 *
 */
@Entity
@Table(name = "account_type")
public class AccountType implements Serializable {
    private static final long serialVersionUID = 4270625962503463818L;

    @Id
    @Column(name = "id_account_type", nullable = false)
    private Long idAccountDetail;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "ceiling")
    private int ceiling;


    public Long getIdAccountDetail() {
        return idAccountDetail;
    }

    public void setIdAccountDetail(Long idAccountDetail) {
        this.idAccountDetail = idAccountDetail;
    }

    public int getCeiling() {
        return ceiling;
    }

    public void setCeiling(int plafonds) {
        this.ceiling = plafonds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
