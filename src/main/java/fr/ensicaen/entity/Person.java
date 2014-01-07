package fr.ensicaen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe héritant de client définissant une personne
 *
 * @author Alexandre Cros
 * @date 06/01/14
 */
@Entity
@Table(name = "person")
public class Person extends Client {
    private static final long serialVersionUID = 1418275098077928373L;

    /**
     * Prénom du client
     */
    @Column(name = "firstname", nullable = false)
    private String firstname;

    /**
     * Nom du client
     */
    @Column(name = "lastname", nullable = false)
    private String lastname;

    public Person() {
    }

    @Override
    public String getName() {
        return this.getFirstname() + " " + this.getLastname();
    }

    public Person(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
