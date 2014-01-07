package fr.ensicaen.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Classe conteneur pour les comptes des utilisateurs
 *
 * @author Jérémie Drouet
 * @date 06/01/14
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 6095491421553685983L;

    /**
     * Identifiant du compte
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_account", nullable = false)
    private Long idAccount;

    /**
     * Solde du compte
     */
    @Column(name = "balance", nullable = false)
    private double balance;

    /**
     * Client à qui appartient le compte
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    /**
     * Type du compte
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "account_type", nullable = false)
    private AccountType accountType;

    /**
     * Liste des cartes liées au compte
     */
    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Card> cardList;

    /**
     * Liste des opérations débitées sur le compte
     */
    @OneToMany(mappedBy = "source", cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Operation> debitList;

    /**
     * Liste des opérations créditées sur le compte
     */
    @OneToMany(mappedBy = "destination", cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Operation> creditList;

    public Account() {
    }

    public Account(Client client, AccountType accountType, double balance) {
        this.client = client;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idCompte) {
        this.idAccount = idCompte;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
