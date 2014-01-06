package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author mhiri
 *
 */

@Table(name = "profil")
public class Profil  implements Serializable {
	
	@OneToOne
	private Long idProfil;
	
	@OneToOne
	private List<Service> services;

	public Long getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Long idProfil) {
		this.idProfil = idProfil;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	

}
