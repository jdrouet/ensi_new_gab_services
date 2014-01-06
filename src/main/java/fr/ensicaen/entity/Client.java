package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * 
 * @author mhiri
 * @date 06/01/2014
 */
@Table(name = "client")
public class Client implements Serializable {
	
	@Id
    @Column(name = "idClient", nullable = false)
	private Long clientId;

	public Long getClientId() {
		return clientId;
	}

	@OneToMany
	private List<Compte> compte;
	
	
	@OneToOne
	private Historique historique;
	
	
    @OneToOne
	private Profil profil;

}
