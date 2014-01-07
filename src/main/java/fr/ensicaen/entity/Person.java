package fr.ensicaen.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */
@Table(name = "person")
public class Person extends Client {
    private static final long serialVersionUID = 1418275098077928373L;

    @Column(name = "firstname", nullable = false)
    private String firstname;
    
    @Column(name = "lastname", nullable = false)
    private String lastname;

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
