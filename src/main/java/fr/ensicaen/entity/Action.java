package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */

@Table (name = "action")
public class Action implements Serializable {
	@Id
	@Column(name = "id_action", nullable = false)
	private Long idAction;
	@Column(nullable = false)
	private Date date;
	
	public Long getIdAction() {
		return idAction;
	}
	public void setIdAction(Long idAction) {
		this.idAction = idAction;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
