package fr.ensicaen.bean;

import fr.ensicaen.entity.Card;
import fr.ensicaen.service.IGenericService;
import fr.ensicaen.service.impl.CardService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.*;

/**
 * User: Jérémie Drouet
 * Date: 06/01/14
 */
@ManagedBean
@SessionScoped
public class AuthenticationBean implements Serializable {
    private static final long serialVersionUID = -9052283682987963522L;
    public static final String PAGE = "/pages/authentication.xhtml";

    private List<Integer> keyboard;
    private List<Integer> cliqued;

    private Card card;
    private String error;

    @ManagedProperty("#{cardService}")
    private IGenericService<Card> cardService;

    @ManagedProperty("#{homeBean}")
    private HomeBean homeBean;

    public AuthenticationBean() {
        this.keyboard = new ArrayList<>();
        this.cliqued = new ArrayList<>();
        //
        for (int i = 0; i < 10; i++) {
            this.keyboard.add(i);
        }
        //
        Collections.shuffle(this.keyboard);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public IGenericService<Card> getCardService() {
        return cardService;
    }

    public void setCardService(IGenericService<Card> cardService) {
        this.cardService = cardService;
    }

    public String getButtonLabel(int i) {
        return this.keyboard.get(i) + "";
    }

    public void clickKeyboard(int key) {
        this.cliqued.add(this.keyboard.get(key));
    }

    public String getStars() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.cliqued.size(); i++) {
            sb.append(" * ");
        }
        return sb.toString();
    }

    private String getClearPin() {
        StringBuffer sb = new StringBuffer();
        for (Integer i : this.cliqued) {
            sb.append(i.toString());
        }
        return sb.toString();
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isKeyboardDisabled() {
        return this.cliqued.size() >= 4;
    }

    public boolean isValidButtonDisabled() {
        return this.cliqued.size() < 4;
    }

    public String onPressCancel() {
        this.cliqued.clear();
        Collections.shuffle(this.keyboard);
        return PAGE;
    }

    public String onPressValid() {
        if (this.card == null) {
            return IndexBean.PAGE;
        } else if (this.card.isPin(this.getClearPin())) {
            this.homeBean.setCard(card);
            return HomeBean.PAGE;
        } else {
            return PAGE;
        }
    }
}
