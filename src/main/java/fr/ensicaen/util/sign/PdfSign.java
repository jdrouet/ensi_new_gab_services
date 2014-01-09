package fr.ensicaen.util.sign;

import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import org.apache.commons.codec.binary.Base64;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

import fr.ensicaen.entity.Client;
import fr.ensicaen.util.Properties;

public class PdfSign {

	private static PathMatcher matcherImg = FileSystems.getDefault()
			.getPathMatcher("glob:*.{png,bmp,jpeg}");
	private static PathMatcher matcherPDF = FileSystems.getDefault()
			.getPathMatcher("glob:*.{pdf}");

	/**
	 * Permet de signer un document PDF/ et ou une image
	 * 
	 * @param client
	 * @param f
	 */
	public static File signPDF(Client client, File f) {

		// et ici sa "passPhrase"
		String fileKeyPassword = Properties.P12_PASSWORD;
		File outputFile = null;
		if (client == null || client.getP12() == null || f == null
				|| !f.exists()) {
			throw new IllegalArgumentException(
					"Client p12 not present or file not exist");
		}
		try {
			File workFile = null;
			if (matcherImg.matches(Paths.get(f.getName()))) {
				workFile = img2Pdf(f);
			} else if (matcherPDF.matches(Paths.get(f.getName()))) {
				workFile = f;
			}
			if (workFile == null) {
				throw new IllegalArgumentException(
						"Unsupported format type to sign document (pdf or img)");
			}

			outputFile = File.createTempFile(workFile.getName(), "-signed.pdf");
			outputFile.deleteOnExit();
			// Creation d'un KeyStore
			KeyStore ks = KeyStore.getInstance("pkcs12");
			ByteArrayInputStream bais = new ByteArrayInputStream(
					Base64.decodeBase64(client.getP12()));
			// Chargement du certificat p12 dans le magasin
			ks.load(bais, fileKeyPassword.toCharArray());
			bais.close();
			String alias = (String) ks.aliases().nextElement();
			// Recupération de la clef privee
			PrivateKey key = (PrivateKey) ks.getKey(alias,
					fileKeyPassword.toCharArray());
			// et de la chaine de certificats
			Certificate[] chain = ks.getCertificateChain(alias);

			// Lecture du document source
			PdfReader pdfReader = new PdfReader(f.getAbsolutePath());

			// Creation du tampon de signature
			PdfStamper pdfStamper;
			pdfStamper = PdfStamper.createSignature(pdfReader, null, '\0',
					outputFile);
			PdfSignatureAppearance sap = pdfStamper.getSignatureAppearance();
			sap.setVisibleSignature(new Rectangle(10, 10, 50, 30), 1,
					"sign_rbl");
			sap.setReason("Banque Certification");
			sap.setLocation("Foobar");

			ExternalSignature es = new PrivateKeySignature(key, "SHA-256", "BC");
			ExternalDigest digest = new BouncyCastleDigest();
			MakeSignature.signDetached(sap, digest, es, chain, null, null,
					null, 0, CryptoStandard.CMS);

			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
		} catch (IOException | DocumentException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputFile;
	}

	/**
	 * Transforme une image en pdf
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static File img2Pdf(File f) throws IOException {
		File r = null;
		// Si c'est pas un pdf, on cree un pdf en partant du principe que
		// c'est une image
		// @TODO filtrer les types de fichier
		if (matcherImg.matches(Paths.get(f.getName()))) {
			r = File.createTempFile(f.getName(), "toPDF.pdf");
			r.deleteOnExit();
			Document document = new Document();
			try {
				// Creation du "writer" vers le doc
				// directement vers un fichier
				PdfWriter.getInstance(document, new FileOutputStream(r));
				// Ouverture du document
				document.open();

				// Ecriture des datas
				Image img = Image.getInstance(Toolkit.getDefaultToolkit()
						.createImage(f.getAbsolutePath()), null);
				document.add(img);
			} catch (IOException | DocumentException e) {
				e.printStackTrace();
			} finally {
				// Fermeture du document
				document.close();
			}

		}
		if (r == null) {
			throw new IllegalArgumentException(
					"Unsupported format type to sign document (pdf or img)");
		}
		return r;
	}
}
