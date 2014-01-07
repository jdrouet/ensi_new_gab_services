package fr.ensicaen.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */
@Entity
@Table(name = "operation")
public class Operation implements Serializable {
    private static final long serialVersionUID = 1628033427391795070L;

    @Id
    @Column(name = "id_operation")
    private Long idOperation;

    @Column(name = "event", nullable = false)
    private Timestamp event;

    @ManyToOne
    @JoinColumn(name = "source", nullable = false)
    private Account source;

    @ManyToOne
    @JoinColumn(name = "destination", nullable = false)
    private Account destination;

    @Column(name = "amout", nullable = false)
	private float amount; /* toujours positif */

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
}
