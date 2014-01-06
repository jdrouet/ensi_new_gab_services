package fr.ensicaen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Table(name = "service")
public class Service implements Serializable{
	@Id
    @Column(name = "id_Service", nullable = false)
	private Long idService;
	
	@Column(nullable = false)
	private List<String> tags;
	
}
