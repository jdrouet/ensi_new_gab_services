package fr.ensicaen.bean;

import fr.ensicaen.entity.Card;
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
@ViewScoped
public class AuthenticationBean implements Serializable {
    private static final long serialVersionUID = -9052283682987963522L;

    private List<Integer> keyboard;
    private List<Integer> cliqued;

    @ManagedProperty("#{cardService}")
    private CardService cardService;

    @ManagedProperty("#{param.idCard}")
    private Long idCard;

    @ManagedProperty("#{param.error}")
    private String error;

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

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public boolean isKeyboardDisabled() {
        return this.cliqued.size() >= 4;
    }

    public boolean isValidButtonDisabled() {
        return this.cliqued.size() < 4;
    }

    public String onPressCancel() {
        Collections.shuffle(this.keyboard);
        return "authentication.xhtml";
    }

    public Card getCard() {
        return this.cardService.find(this.getIdCard());
    }

    public String onPressValid() {
        Card card = this.getCard();
        if (card == null) {
            return "/pages/index.html";
        } else if (card.isPin(this.getClearPin())) {
            this.homeBean.setCard(card);
            return "/pages/home.xhtml";
        } else {
            return "/pages/authentication.xhtml?error=index.wrong_pin&idCard=" + this.idCard;
        }
    }
}
