package fr.ensicaen.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * @author mhiri
 * @date 06/01/2014
 */
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client implements Serializable {
    private static final long serialVersionUID = -4995265848265187571L;

    @Id
    @Column(name = "id_client", nullable = false)
    private Long idClient;

    @OneToMany(mappedBy = "id_client")
    private List<Account> accountList;

    @OneToMany(mappedBy = "id_client")
    private List<Service> serviceList;

    @OneToMany(mappedBy = "id_client")
    private List<Action> actionList;

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
