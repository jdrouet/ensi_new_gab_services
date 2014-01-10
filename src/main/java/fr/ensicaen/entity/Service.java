package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Classe définissant un service
 * 
 * @author Jérémie Drouet
 */
@Entity
@Table(name = "service")
public class Service implements Serializable {
	private static final long serialVersionUID = 3375875149325438194L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_service", nullable = false)
	private Long idService;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "cost", nullable = false)
	private float cost;

	@Column(name = "removable", nullable = false)
	private boolean removable;

	/**
	 * Chemin d'accès au service
	 */
	@Column(name = "path", nullable = false)
	private String path;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "service_tag", joinColumns = { @JoinColumn(name = "id_service") }, inverseJoinColumns = { @JoinColumn(name = "id_tag") })
	private List<Tag> tagList;

	public Service() {
	}

	public Service(String name, String description, String path) {
		this(name, description, path, 0, true);
	}

	public Service(String name, String description, String path, float cost) {
		this(name, description, path, cost, true);
	}

	public Service(String name, String description, String path, float cost,
			boolean removable) {
		this.name = name;
		this.description = description;
		this.removable = removable;
		this.path = path;
		this.cost = cost;
	}

	public Long getIdService() {
		return idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRemovable() {
		return removable;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idService == null) ? 0 : idService.hashCode());
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
		Service other = (Service) obj;
		if (idService == null) {
			if (other.idService != null)
				return false;
		} else if (!idService.equals(other.idService))
			return false;
		return true;
	}

}
