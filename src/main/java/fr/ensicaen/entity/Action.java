package fr.ensicaen.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * Classe conteneur pour les actions d'activation et de desactivation des service
 *
 * @author Alexandre Cros
 * @date 06/01/14
 */
@Entity
@Table(name = "action")
public class Action implements Serializable {
    private static final long serialVersionUID = -3280786224272692182L;
    /**
     * Identifiant de l'action
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_action", nullable = false)
    private Long idAction;

    /**
     * Date de l'evenement
     */
    @Column(name = "event", nullable = false)
    private Timestamp event;

    /**
     * Client effectuant l'action
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    /**
     * Service activé ou desactivé par le client
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "service", nullable = false)
    private Service service;

    /**
     * Booléen définissant si le service est activé ou desactivé
     */
    @Column(name = "activation", nullable = false)
    private boolean activation;

    public Action() {
    }

    public Action(Client client, Service service, boolean activation) {
        this.event = new Timestamp(new Date().getTime());
        this.client = client;
        this.service = service;
        this.activation = activation;
    }

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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }
}
