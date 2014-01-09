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
    private String result;


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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void openDialog(Command command) {
        this.setCommand(command);
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

    }

    public static abstract class Command implements Serializable {
        private static final long serialVersionUID = 4979412193763108182L;

        private String nextPage;

        public abstract void execute();

        public String getNextPage() {
            return nextPage;
        }

        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }
    }

}
