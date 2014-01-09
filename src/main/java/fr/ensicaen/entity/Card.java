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
import org.bouncycastle.util.encoders.Base64;

import fr.ensicaen.util.CryptoUtils;

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
	 * Cle privee permettant de chiffrer le template des minuties. Elle est
	 * chiffree par le pin
	 */
	@Column(name = "cipher_key")
	private String key;

	/**
	 * Template chiffre de l'utilisateur (minuties). base64 pour eviter les
	 * problemes d'encodage
	 */
	@Column(name = "cipherTemplate", columnDefinition = "text")
	private String cipherTemplate;

	/**
	 * Compte auquel est lié la carte
	 */
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "account", nullable = false)
	private Account account;

	public Card() {
	}

	public Card(Account account, String pan, String clearPin,
			byte[] cleartemplate) {
		// generation d'une cle de chiffrement
		key = new BigInteger(130, new Random()).toString(32);
		this.account = account;
		this.pan = pan;
		this.setClearPin(clearPin);

		// chiffrement et passage en base64 du template
		cipherTemplate = Base64.toBase64String(CryptoUtils.xor(cleartemplate,
				key.getBytes()));

		// chiffrement de la cle par le pin
		key = Base64.toBase64String(CryptoUtils.xor(key.getBytes(),
				clearPin.getBytes()));
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

	public String getCipherTemplate() {
		return cipherTemplate;
	}

	public void setCipherTemplate(String cipherTemplate) {
		this.cipherTemplate = cipherTemplate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((cipherTemplate == null) ? 0 : cipherTemplate.hashCode());
		result = prime * result
				+ ((hashedPin == null) ? 0 : hashedPin.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((pan == null) ? 0 : pan.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (cipherTemplate == null) {
			if (other.cipherTemplate != null)
				return false;
		} else if (!cipherTemplate.equals(other.cipherTemplate))
			return false;
		if (hashedPin == null) {
			if (other.hashedPin != null)
				return false;
		} else if (!hashedPin.equals(other.hashedPin))
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (pan == null) {
			if (other.pan != null)
				return false;
		} else if (!pan.equals(other.pan))
			return false;
		return true;
	}

}
