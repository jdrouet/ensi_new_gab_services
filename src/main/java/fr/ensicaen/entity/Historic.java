package fr.ensicaen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "historic")
public class Historic implements Serializable{
	
	@Id
    @Column(name = "id_historic", nullable = false)
	private Long idHistoric;

	@OneToMany
	private List<Action> actions;
	
	public Long getIdHistoric() {
		return idHistoric;
	}
	public void setIdHistoric(Long idHistorique) {
		this.idHistoric = idHistorique;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}
