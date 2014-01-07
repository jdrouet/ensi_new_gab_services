package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author mhiri
 * @date 06/01/2014
 */
@Table(name = "organization")
public class Organization extends Client implements Serializable {
    private static final long serialVersionUID = 8240671633020666134L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
