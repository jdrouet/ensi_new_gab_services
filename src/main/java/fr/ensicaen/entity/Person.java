package fr.ensicaen.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

/**
 * Classe héritant de client définissant une personne
 * 
 * @author Alexandre Cros
 * @date 06/01/14
 */
@Entity
@Table(name = "person")
public class Person extends Client {
	private static final long serialVersionUID = 1418275098077928373L;

	/**
	 * Prénom du client
	 */
	@Column(name = "firstname", nullable = false)
	private String firstname;

	/**
	 * Nom du client
	 */
	@Column(name = "lastname", nullable = false)
	private String lastname;

	public Person() {
	}

	@Override
	public String getName() {
		return this.getFirstname() + " " + this.getLastname();
	}

	public Person(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Person(String firstname, String lastname, String email) {
		this(firstname, lastname);
		setEmail(email);
		generateP12();
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public void generateP12() {
		if (getEmail() == null || firstname == null || lastname == null)
			return;
		// @TODO le placer pour qu'il devienne static
		Security.addProvider(new BouncyCastleProvider());
		try {
			// recuperation du CA de la banque
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
					Files.readAllBytes(Paths.get(Person.class.getResource(
							"/certificate/ca.key").toURI())));
			KeyFactory generator = KeyFactory.getInstance("RSA");
			PrivateKey keysCA = generator.generatePrivate(privateKeySpec);
			X509CertificateHolder certCA = new X509CertificateHolder(
					Files.readAllBytes(Paths.get(Person.class.getResource(
							"/certificate/ca.crt").toURI())));

			Certificate crtCA = new JcaX509CertificateConverter().setProvider(
					"BC").getCertificate(certCA);
			PublicKey keypCA = crtCA.getPublicKey();

			// creation du crt user
			// tirage bicle rsa
			KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA", "BC");
			kpGen.initialize(1024, new SecureRandom());
			KeyPair pairUser = kpGen.generateKeyPair();

			Date notBefore = new Date(System.currentTimeMillis() - 24 * 60 * 60);
			Date notAfter = new Date(System.currentTimeMillis() + 10 * 24 * 60
					* 60 * 365);
			BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());

			X500NameBuilder nbUser = new X500NameBuilder(BCStyle.INSTANCE);
			nbUser.addRDN(BCStyle.CN, this.lastname + " " + this.firstname);
			nbUser.addRDN(BCStyle.EmailAddress, getEmail());
			X509v3CertificateBuilder cbUser = new JcaX509v3CertificateBuilder(
					certCA.getSubject(), serial, notBefore, notAfter,
					nbUser.build(), pairUser.getPublic());

			// signature par la CA
			ContentSigner sigGen = new JcaContentSignerBuilder(
					"SHA256WithRSAEncryption").setProvider(
					BouncyCastleProvider.PROVIDER_NAME).build(keysCA);

			X509CertificateHolder certUser = cbUser.build(sigGen);

			// creation du p12
			KeyStore store = KeyStore.getInstance("PKCS12", "BC");
			store.load(null, null);
			Certificate[] certs = new Certificate[1];
			certs[0] = new JcaX509CertificateConverter().setProvider("BC")
					.getCertificate(certUser);
			store.setKeyEntry(this.lastname + " " + this.firstname,
					pairUser.getPrivate(), null, certs);

			// stockage p12 (b64) avec mot de passe
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			/*
			 * try (OutputStream os = new OutputStream() { private StringBuilder
			 * string = new StringBuilder();
			 * 
			 * @Override public void write(int arg0) throws IOException {
			 * string.append(arg0); }
			 * 
			 * public String toString() { return
			 * Base64.encodeBase64String(string.toString() .getBytes()); } }) {
			 */
			store.store(baos, "ensicaen".toCharArray());
			setP12(Base64.encodeBase64String(baos.toByteArray()));

		} catch (IOException | URISyntaxException | NoSuchAlgorithmException
				| InvalidKeySpecException | OperatorCreationException
				| KeyStoreException | NoSuchProviderException
				| CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
