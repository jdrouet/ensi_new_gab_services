package fr.ensicaen.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * Classe conteneur du client générique
 *
 * @author mhiri
 * @date 06/01/2014
 */
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client implements Serializable {
    private static final long serialVersionUID = -4995265848265187571L;

    /**
     * Identifiant du client dans la base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client", nullable = false)
    private Long idClient;

    /**
     * Liste des comptes liés au client
     */
    @OneToMany(mappedBy = "client", cascade = {CascadeType.ALL})
    private List<Account> accountList;

    /**
     * Liste des services activés au client
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinTable(name = "client_service",
            joinColumns = {@JoinColumn(name = "id_client")},
            inverseJoinColumns = {@JoinColumn(name = "id_service")})
    private List<Service> serviceList;

    /**
     * Liste des actions d'activation et de desactivation des services par le client
     */
    @OneToMany(mappedBy = "client", cascade = {CascadeType.ALL})
    private List<Action> actionList;

    public Client() {
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }
}
