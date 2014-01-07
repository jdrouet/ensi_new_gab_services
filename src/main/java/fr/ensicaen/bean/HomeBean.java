package fr.ensicaen.bean;

import fr.ensicaen.entity.Card;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class HomeBean implements Serializable {
    private static final long serialVersionUID = -4523859625680715721L;

    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
