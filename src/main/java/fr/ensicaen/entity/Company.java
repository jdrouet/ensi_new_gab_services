package fr.ensicaen.entity;

import javax.persistence.*;

import java.io.Serializable;


/**
 * @author mhiri
 */
@Entity
@Table(name = "company")
public class Company extends Client implements Serializable {
    private static final long serialVersionUID = 2611603814153599254L;

    @Column(name = "address")
    private String address;

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
}
