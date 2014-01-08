package fr.ensicaen.bean;

import fr.ensicaen.entity.Card;

import javax.faces.bean.*;
import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {
    private static final long serialVersionUID = -7049223030318943085L;
    public static final String PAGE = "/pages/index.xhtml";

    @ManagedProperty("#{authenticationBean}")
    private AuthenticationBean authenticationBean;

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public String getPicturePath(Card card) {
        return String.format("/img/fake_card_%d.jpg", (card.getIdCard() % 2) + 1);
    }

    public String onSelectCard(Card card) {
        this.authenticationBean.setCard(card);
        return AuthenticationBean.PAGE;
    }
}
