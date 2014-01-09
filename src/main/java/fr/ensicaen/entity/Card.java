package fr.ensicaen.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Cette classe correspond a la carte bancaire de l'utilisateur. Utilise pour
 * les besoins du projet, en vrai elle ne serait pas geree par le serveur.
 *
 * @author JM
 */
@Entity
@Table(name = "card")
public class Card implements Serializable {
    private static final long serialVersionUID = -7336446907414202184L;

    /**
     * Identifiant unique de la carte
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_card")
    private Long idCard;

    /**
     * PAN de la carte
     */
    @Column(name = "pan", nullable = false, unique = true)
    private String pan;

    /**
     * PIN hashé de la carte En pratique, celui ci doit être stocké dans la
     * carte physique et non en base de données, mais à des fins de
     * démonstrations, comme nous n'utilisons pas de carte physique, nous
     * stockons le hash du PIN en base de données
     */
    @Column(name = "hashed_pin", nullable = false)
    private String hashedPin;

    /**
     * Cle privee permettant de chiffrer le template des minuties
     */
    @Column(name = "cipher_key")
    private String key;

    /**
     * Compte auquel est lié la carte
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public Card() {
    }

    public Card(Account account, String pan, String clearPin) {
        key = new BigInteger(130, new Random()).toString(32);
        this.account = account;
        this.pan = pan;
        this.setClearPin(clearPin);
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getHashedPin() {
        return hashedPin;
    }

    public void setHashedPin(String hashedPin) {
        this.hashedPin = hashedPin;
    }

    public void setClearPin(String clearPin) {
        this.setHashedPin(sha1(clearPin));
    }

    public boolean isPin(String clearPin) {
        return this.hashedPin.equals(sha1(clearPin));
    }

    private static String sha1(String pin) {
        return DigestUtils.sha1Hex(pin);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
