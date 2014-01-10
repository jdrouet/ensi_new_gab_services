package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe caractérisant un TAG
 * 
 * @author Jérémie Drouet
 * @date 07/01/14
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable {
	private static final long serialVersionUID = 8351247950608630896L;

	/**
	 * Identifiant du TAG
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tag")
	private Long idTag;

	/**
	 * Nom du TAG
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public Tag() {
	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public Long getIdTag() {
		return idTag;
	}

	public void setIdTag(Long idServiceTag) {
		this.idTag = idServiceTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTag == null) ? 0 : idTag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (idTag == null) {
			if (other.idTag != null)
				return false;
		} else if (!idTag.equals(other.idTag))
			return false;
		return true;
	}

}
