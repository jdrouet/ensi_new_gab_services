package service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.AccountType;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Person;
import fr.ensicaen.util.sign.PdfSign;

public class SignPdf {

	private Person p1;
	private Account a1;
	private AccountType t1;
	private Card card;

	@Before
	public void setUp() throws Exception {
		t1 = new AccountType();
		t1.setName("Livret A");
		t1.setCeiling(22000.0);

		p1 = new Person("Hervé", "Tété", "herve.tete@gmail.com", null);
		//
		a1 = new Account(p1, t1, 42.0);
		//
		card = new Card(a1, "123456789012345", "1234");
		a1.setCardList(Arrays.asList(card));
		//
		p1.setAccountList(Arrays.asList(a1));
	}

	@Test
	public void testSign() throws URISyntaxException {
		System.out.println(p1.getP12());
		PdfSign signService = new PdfSign();
		File pdfpath = new File(SignPdf.class.getResource("/pdf/ticket.pdf")
				.toURI());
		System.out.println(pdfpath.getAbsolutePath());
		File f = signService.signPDF(p1, pdfpath);
		System.out.println(f.getAbsolutePath());
		Assert.assertTrue(f.exists());
		// f.delete();
	}
}
