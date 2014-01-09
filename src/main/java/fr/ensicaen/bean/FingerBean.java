package fr.ensicaen.bean;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Client;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jérémie Drouet
 * Date: 08/01/14
 */
@ManagedBean
@SessionScoped
public class FingerBean extends AbstractBean {
    private static final long serialVersionUID = 4819179251339653633L;
    public static final String PAGE = "/pages/finger/finger.xhtml";

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    private Command command;
    private int result;


    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Client getClient() {
        return this.homeBean.getClient();
    }

    public Account getAccount() {
        return this.homeBean.getAccount();
    }

    public Card getCard() {
        return this.homeBean.getCard();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void openDialog(Command command) {
        this.setCommand(command);
        this.setResult(-1);
        //
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("closable", false);
        options.put("contentWidth", 250);
        options.put("contentHeight", 350);
        //hint: available options are modal, draggable, resizable, width, height, contentWidth and contentHeight
        RequestContext.getCurrentInstance().openDialog("finger/finger", options, null);
    }

    public void proceed() {
        if (this.result == 0) {
            this.getCommand().onSuccess();
            this.pushInfoNotification("Succes", "Operation effectuée, vous pouvez fermer la fenetre");
            RequestContext.getCurrentInstance().closeDialog(null);
        } else {
            this.getCommand().onFail();
            this.pushErrorNotification("Erreur", "Authentification echouée");
        }
    }

    public static abstract class Command implements Serializable {
        private static final long serialVersionUID = 4979412193763108182L;

        public abstract void onSuccess();

        public abstract void onFail();

    }

}
