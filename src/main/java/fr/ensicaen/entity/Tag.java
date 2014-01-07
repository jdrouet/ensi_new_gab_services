package fr.ensicaen.entity;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Classe caractérisant un TAG
 *
 * @author  Jérémie Drouet
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
