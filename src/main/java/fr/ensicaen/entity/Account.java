package fr.ensicaen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "account_type", nullable = false)
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<Card> cardList;

    @OneToMany(mappedBy = "source")
    private List<Operation> debitList;

    @OneToMany(mappedBy = "destination")
    private List<Operation> creditList;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idCompte) {
        this.idAccount = idCompte;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<Operation> getDebitList() {
        return debitList;
    }

    public void setDebitList(List<Operation> debitList) {
        this.debitList = debitList;
    }

    public List<Operation> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Operation> creditList) {
        this.creditList = creditList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
