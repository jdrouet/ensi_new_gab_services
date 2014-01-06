package fr.ensicaen.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 06/01/14
 */
@Table(name = "compte")
public class Compte implements Serializable {
    private static final long serialVersionUID = 6095491421553685983L;

    @Id
    @Column(name = "id_compte", nullable = false)
    private Long idCompte;

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }
}
