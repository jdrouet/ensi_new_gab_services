package fr.ensicaen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "historique")
public class Historique implements Serializable{
	
	@Id
    @Column(name = "id_historique", nullable = false)
	private Long idHistorique;
	@OneToMany
	private List<Action> actions;
	
	public Long getIdHistorique() {
		return idHistorique;
	}
	public void setIdHistorique(Long idHistorique) {
		this.idHistorique = idHistorique;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}
