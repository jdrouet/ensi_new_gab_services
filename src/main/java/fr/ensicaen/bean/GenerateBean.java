package fr.ensicaen.bean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.AccountType;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Client;
import fr.ensicaen.entity.Company;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.entity.Person;
import fr.ensicaen.entity.Service;
import fr.ensicaen.entity.Tag;
import fr.ensicaen.service.IGenericService;

/**
 * User: Jérémie Drouet Date: 07/01/14
 */
@ManagedBean
@ViewScoped
public class GenerateBean extends AbstractBean {
	private static final long serialVersionUID = 4046950810051100853L;

	@ManagedProperty("#{clientService}")
	private IGenericService<Client> clientService;

	public IGenericService<Client> getClientService() {
		return clientService;
	}

	public void setClientService(IGenericService<Client> clientService) {
		this.clientService = clientService;
	}

	public void generate() throws IOException, URISyntaxException {
		/** Types de comptes **/
		//
		AccountType t1 = new AccountType();
		t1.setName("Livret A");
		t1.setCeiling(22000.0);
		//
		AccountType t2 = new AccountType();
		t2.setName("Livret jeune");
		t2.setCeiling(22000.0);
		t2.setRate(0.0325);
		//
		AccountType t3 = new AccountType();
		t2.setName("Compte Diamant");
		//

		//
		AccountType t4 = new AccountType();
		t4.setName("Compte partenaire");

		/** Tags **/
		Tag tag1 = new Tag("SNCF");
		Tag tag2 = new Tag("Signature");
		Tag tag3 = new Tag("Reservation");
		Tag tag4 = new Tag("Consultation");
		Tag tag5 = new Tag("Compte");

		/** Services **/
		//
		Service s1 = new Service("SNCF", "Service d'achat de billets SNCF",
				"sncf");
		s1.setTagList(Arrays.asList(tag1, tag3));
		//
		Service s2 = new Service("Signature", "Signature de document", "sign");
		s2.setTagList(Arrays.asList(tag2));
		//
		Service s3 = new Service("Consultation", "Consultation des comptes",
				"consult");
		s3.setTagList(Arrays.asList(tag4, tag5));

		//
		/** Personne 1 **/
		//
		Person p1 = new Person("Hervé", "Tété");
		//
		Account a1 = new Account(p1, t1, 42.0);
		//
		byte[] data = Files.readAllBytes(Paths.get(GenerateBean.class
				.getResource("/enrollment/jdrouet.fpt").toURI()));
		Card card = new Card(a1, "123456789012345", "1234", data);
		//
		a1.setCardList(Arrays.asList(card));
		//
		p1.setAccountList(Arrays.asList(a1));
		//
		p1.setServiceList(Arrays.asList(s1, s3));
		//
		this.clientService.add(p1);
		//
		/** Personne 2 **/
		//
		Person p2 = new Person("Myriam", "Martin");
		//
		Account a2 = new Account(p2, t2, -62.0);
		//
		data = Files.readAllBytes(Paths.get(GenerateBean.class.getResource(
				"/enrollment/jmarginier.fpt").toURI()));
		Card card2 = new Card(a2, "4973166250789456", "1000", data);
		//
		List<Service> profile2 = new ArrayList<Service>();
		profile2.add(s2);
		profile2.add(s3);
		//
		a2.setCardList(Arrays.asList(card2));
		//
		p2.setAccountList(Arrays.asList(a2));
		//
		p2.setServiceList(profile2);
		//
		Operation o1 = new Operation(a1, a2, 10);
		Operation o2 = new Operation(a2, a1, 15);
		//
		a1.setDebitList(Arrays.asList(o1));
		a2.setDebitList(Arrays.asList(o2));
		a2.setCreditList(Arrays.asList(o1));
		a1.setCreditList(Arrays.asList(o2));
		//
		this.clientService.add(p2);
		//
		//
		/** Personne 3 **/
		//
		Person p3 = new Person("Bob", "Dupond");
		//
		Account a3 = new Account(p3, t1, 6200.0);
		//
		Card card3 = new Card(a3, "9876543210456789", "3210", data);
		//
		a3.setCardList(Arrays.asList(card3));
		//
		p3.setAccountList(Arrays.asList(a3));
		//
		p3.setServiceList(Arrays.asList(s3));
		//
		this.clientService.add(p3);
		//
		/** Personne 4 **/
		//
		Person p4 = new Person("Johnny", "Haliday");
		//
		Account a4 = new Account(p4, t3, 2000000.0);
		Account a5 = new Account(p4, t3, 40000000.0);
		//
		Card card4 = new Card(a4, "4718526394560123", "3141", data);
		Card card5 = new Card(a5, "4567892317514326", "4831", data);
		//
		a4.setCardList(Arrays.asList(card4));
		a5.setCardList(Arrays.asList(card5));
		//
		p4.setAccountList(Arrays.asList(a4, a5));
		//
		p4.setServiceList(Arrays.asList(s1, s2, s3));
		//
		// this.clientService.add(p4);

		//
		/** SNCF **/
		//
		Company sncf = new Company(
				"2 place de la Défense - CNIT 1 - BP 440 - 92053 Paris La Défense Cedex",
				"sncf");
		//
		Account aSncf = new Account(sncf, t4, 0);
		//
		sncf.setAccountList(Arrays.asList(aSncf));
		this.clientService.add(sncf);
	}

}
