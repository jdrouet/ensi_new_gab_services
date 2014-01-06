package fr.ensicaen.bean;

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
public class AuthentificationBean implements Serializable {
    private static final long serialVersionUID = -9052283682987963522L;

    private List<Integer> keyboard;
    private List<Integer> cliqued;

    @ManagedProperty("#{param.error}")
    private String error;

    public AuthentificationBean() {
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

    public boolean isKeyboardDisabled() {
        return this.cliqued.size() >= 4;
    }

    public boolean isValidButtonDisabled() {
        return this.cliqued.size() < 4;
    }

    public String onPressCancel() {
        Collections.shuffle(this.keyboard);
        return "index.xhtml";
    }

    public String onPressValid() {
        if (new Random().nextBoolean()) {
            return "index.xhtml";
        } else {
            return "index.xhtml?error=index.wrong_pin";
        }
    }
}
