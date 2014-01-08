package fr.ensicaen.bean.service.sign;

import fr.ensicaen.bean.HomeBean;
import fr.ensicaen.util.sign.PdfSign;
import org.primefaces.event.FileUploadEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 08/01/14
 */
@ManagedBean
@SessionScoped
public class SignatureBean implements Serializable {
    private static final long serialVersionUID = -3183230042838768890L;

    /*
    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    private String mailRecipient;

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

    public File getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    */

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println(event);
        /*
        File tmp;
        try {
            tmp = File.createTempFile("coucou_joan", event.getFile().getFileName());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        tmp.deleteOnExit();
        try {
            IOUtils.copy(event.getFile().getInputstream(), new FileOutputStream(tmp));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.setUploadedFile(tmp);
        */
    }

    /*
    public String sendFile() {
        File signedFile = PdfSign.signPDF(this.getHomeBean().getClient(), this.getUploadedFile());
        // TODO send file
        //
        return HomeBean.PAGE;
    }
    */
}
