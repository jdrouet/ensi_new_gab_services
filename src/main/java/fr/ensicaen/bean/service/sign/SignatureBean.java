package fr.ensicaen.bean.service.sign;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import fr.ensicaen.bean.AbstractBean;
import fr.ensicaen.bean.FingerBean;
import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.util.mailSender.EnsicaenSender;
import fr.ensicaen.util.sign.PdfSign;

/**
 * User: Jérémie Drouet Date: 08/01/14
 */
@ManagedBean
@SessionScoped
public class SignatureBean extends AbstractBean {
	private static final long serialVersionUID = -3183230042838768890L;
	private static final String PAGE = "/pages/service/sign/index.xhtml";

	@ManagedProperty("#{homeBean}")
	private HomeBean homeBean;

	@ManagedProperty("#{fingerBean}")
	private FingerBean fingerBean;

	private String mailRecipient;

	private String filename;

	private File uploadedFile;

	public String getMailRecipient() {
		return mailRecipient;
	}

	public void setMailRecipient(String mailRecipient) {
		this.mailRecipient = mailRecipient;
	}

	public HomeBean getHomeBean() {
		return homeBean;
	}

	public void setHomeBean(HomeBean homeBean) {
		this.homeBean = homeBean;
	}

	public FingerBean getFingerBean() {
		return fingerBean;
	}

	public void setFingerBean(FingerBean fingerBean) {
		this.fingerBean = fingerBean;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.setFilename(event.getFile().getFileName());
		File tmp;
		try {
			tmp = File.createTempFile("coucou_joan", event.getFile()
					.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		tmp.deleteOnExit();
		try {
			IOUtils.copy(event.getFile().getInputstream(),
					new FileOutputStream(tmp));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		this.setUploadedFile(tmp);
	}

	public String sendFile() {
		this.fingerBean.initialize(new FingerBean.Command() {
			private static final long serialVersionUID = 5665367668948607128L;

			@Override
			public void onSuccess() {
				sendFileCommand();
			}

			@Override
			public void onFail() {
				// To change body of implemented methods use File | Settings |
				// File Templates.
			}
		}, PAGE);
		return FingerBean.PAGE;
	}

	public String sendFileCommand() {
		File signedFile = PdfSign.signPDF(this.getHomeBean().getClient(),
				this.getUploadedFile());
		EnsicaenSender sender = new EnsicaenSender();
		try {
			sender.sendFile(this.getHomeBean().getClient().getName(),
					this.mailRecipient, signedFile);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		return HomeBean.PAGE;
	}
}
