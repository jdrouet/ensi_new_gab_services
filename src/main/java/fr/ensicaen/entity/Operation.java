package fr.ensicaen.entity;
import javax.persistence.*;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */

@Table(name = "operation")
public class Operation extends Action{
	@Column(nullable = false)
	int montantOperation; /*negatif ou positif*/

	public int getMontantOperation() {
		return montantOperation;
	}

	public void setMontantOperation(int montantOperation) {
		this.montantOperation = montantOperation;
	}
}
