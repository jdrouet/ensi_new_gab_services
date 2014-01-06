package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author mhiri
 * @date 06/01/2014
 */
@Table(name = "organisation")
public class Organisation  extends Client implements Serializable {
	
	
	public int getOrganizationId() {
		return idOrganization;
	}

	@OneToOne
	private int idOrganization;
	

}
