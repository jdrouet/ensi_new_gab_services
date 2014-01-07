package fr.ensicaen.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * User: Alexandre Cros
 * Date: 06/01/14
 */
@Entity
@Table(name = "action")
public class Action implements Serializable {
    private static final long serialVersionUID = -3280786224272692182L;
    @Id
    @Column(name = "id_action", nullable = false)
    private Long idAction;

    @Column(name = "event", nullable = false)
    private Timestamp event;

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    public Long getIdAction() {
        return idAction;
    }

    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }

    public Timestamp getEvent() {
        return event;
    }

    public void setEvent(Timestamp event) {
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
