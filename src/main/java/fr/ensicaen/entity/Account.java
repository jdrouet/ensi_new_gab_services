package fr.ensicaen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 06/01/14
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 6095491421553685983L;

    @Id
    @Column(name = "id_account", nullable = false)
    private Long idAccount;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idCompte) {
        this.idAccount = idCompte;
    }
}
