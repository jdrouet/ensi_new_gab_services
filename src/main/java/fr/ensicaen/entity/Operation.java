package fr.ensicaen.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe définissant les transactions effectuées entre comptes
 * 
 * @author Alexandre Cros
 * @date 06/01/14
 */
@Entity
@Table(name = "operation")
public class Operation implements Serializable {
	private static final long serialVersionUID = 1628033427391795070L;

	/**
	 * Identifiant de la transaction
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_operation")
	private Long idOperation;

	/**
	 * Date de la transaction
	 */
	@Column(name = "event", nullable = false)
	private Timestamp event;

	/**
	 * Compte source de la transaction
	 */
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "source", nullable = false)
	private Account source;

	/**
	 * Compte destination de la transaction
	 */
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "destination", nullable = false)
	private Account destination;

	/**
	 * Montant de la transaction
	 */
	@Column(name = "amout", nullable = false)
	private float amount; /* toujours positif */

	public Operation() {
	}

	public Operation(Account source, Account destination, float amount) {
		this.setEvent(new Timestamp(new Date().getTime()));
		this.source = source;
		this.destination = destination;
		this.amount = amount;
	}

	public Long getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(Long idOperation) {
		this.idOperation = idOperation;
	}

	public Timestamp getEvent() {
		return event;
	}

	public void setEvent(Timestamp event) {
		this.event = event;
	}

	public Account getSource() {
		return source;
	}

	public void setSource(Account source) {
		this.source = source;
	}

	public Account getDestination() {
		return destination;
	}

	public void setDestination(Account destination) {
		this.destination = destination;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOperation == null) ? 0 : idOperation.hashCode());
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
		Operation other = (Operation) obj;
		if (idOperation == null) {
			if (other.idOperation != null)
				return false;
		} else if (!idOperation.equals(other.idOperation))
			return false;
		return true;
	}
}
