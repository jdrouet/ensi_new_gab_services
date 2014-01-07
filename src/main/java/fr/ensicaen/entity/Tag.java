package fr.ensicaen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = 8351247950608630896L;

    @Id
    @Column(name = "id_tag")
    private Long idTag;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

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

	public Tag(String name) {
		super();
		this.name = name;
	}
    
    
}
