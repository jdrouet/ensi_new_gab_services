package fr.ensicaen.bean;

import fr.ensicaen.entity.Card;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@ViewScoped
public class IndexBean {

    public String getAuthenticationPage(Card card) {
        return "/pages/authentication.xhtml?idCard=" + card.getIdCard();
    }

}
