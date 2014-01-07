package fr.ensicaen.entity;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.security.MessageDigest;

import javax.persistence.*;

/**
 * @author mhiri
 */
@Entity
@Table(name = "carte")
public class Card implements Serializable {
    private static final long serialVersionUID = -7336446907414202184L;

    @Id
    @Column(name = "id_card")
    private Long idCard;

    @Column(name = "pan", nullable = false, unique = true)
    private String pan;

    @Column(name = "hashed_pin", nullable = false)
    private String hashedPin;

    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public Card() {
    }

    public Card(Account account, String pan, String clearPin) {
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
        return new String(DigestUtils.sha1(pin));
    }
}
