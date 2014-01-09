package fr.ensicaen.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

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

    @Column(name = "removable", nullable = false)
    private boolean removable;

    /**
     * Chemin d'accès au service
     */
    @Column(name = "path", nullable = false)
    private String path;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "service_tag",
            joinColumns = {@JoinColumn(name = "id_service")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")})
    private List<Tag> tagList;

    public Service() {
    }

    public Service(String name, String description, String path) {
        this(name, description, path, true);
    }

    public Service(String name, String description, String path, boolean removable) {
        this.name = name;
        this.description = description;
        this.removable = removable;
        this.path = path;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            if (o == null) {
                return false;
            } else if (this.getClass() != o.getClass()) {
                return false;
            } else {
                Service service = (Service) o;
                if (this.idService != null) {
                    return this.idService == service.idService;
                } else {
                    return false;
                }
            }
        }
    }
}
