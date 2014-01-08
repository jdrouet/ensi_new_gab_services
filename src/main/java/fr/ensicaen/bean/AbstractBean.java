package fr.ensicaen.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 08/01/14
 */
public abstract class AbstractBean implements Serializable {
    private static final long serialVersionUID = 521786042732787153L;

    protected void pushNotification(FacesMessage.Severity level, String title, String content) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(level, title, content));
    }

    protected void pushErrorNotification(String title, String content) {
        this.pushNotification(FacesMessage.SEVERITY_ERROR, title, content);
    }

    protected void pushWarningNotification(String title, String content) {
        this.pushNotification(FacesMessage.SEVERITY_WARN, title, content);

    }

    protected void pushInfoNotification(String title, String content) {
        this.pushNotification(FacesMessage.SEVERITY_INFO, title, content);

    }

}
