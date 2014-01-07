package fr.ensicaen.bean;

import fr.ensicaen.entity.Card;
import fr.ensicaen.entity.Service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class HomeBean implements Serializable {
    private static final long serialVersionUID = -4523859625680715721L;
    public static final String PAGE = "/pages/home.xhtml";

    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Service> getSelectedServiceList() {
        if (this.card == null) {
            return Collections.emptyList();
        } else if (this.card.getAccount() == null) {
            return Collections.emptyList();
        } else if (this.card.getAccount().getClient() == null) {
            return Collections.emptyList();
        } else {
            return this.card.getAccount().getClient().getServiceList();
        }
    }

    public String getServicePage(Service service) {
        System.out.println("/pages/services/" + service.getPath() + "/index.xhtml");
        return "/pages/services/" + service.getPath() + "/index.xhtml";
    }
}
