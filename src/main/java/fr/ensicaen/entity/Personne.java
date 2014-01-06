package fr.ensicaen.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */
@Table(name = "personne")
public class Personne extends Client {

    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
