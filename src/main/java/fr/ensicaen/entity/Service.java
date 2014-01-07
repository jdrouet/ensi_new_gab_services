package fr.ensicaen.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service")
public class Service implements Serializable {
    private static final long serialVersionUID = 3375875149325438194L;

    @Id
    @Column(name = "id_service", nullable = false)
    private Long idService;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "path", nullable = false)
    private String path;

	@ManyToMany
    @JoinTable(name = "service_tag",
            joinColumns = {@JoinColumn(name = "id_service")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")})
    private List<Tag> tagList;

    public Service() {
    }

    public Service(String name, String description) {
        this.name = name;
        this.description = description;
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
}
