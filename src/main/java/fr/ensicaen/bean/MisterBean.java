package fr.ensicaen.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * User: Jérémie Drouet
 * Date: 08/01/14
 */
@ManagedBean
@SessionScoped
public class MisterBean extends AbstractBean {
    private static final long serialVersionUID = -2187617798778043653L;

    private int[] panel;

    public MisterBean() {
        this.panel = new int[9];
        //
        this.initPanel();
    }

    public void initPanel() {
        for (int i = 0; i < panel.length; i++) {
            this.panel[i] = 0;
        }
    }

    public int getAt(int i) {
        return this.panel[i];
    }

    public void setAt(int i, int val) {
        this.panel[i] = val;
    }

    public void playAt(int i) {
        this.setAt(i, 1);
        int next = this.getNext();
        if (next >= 0) {
            this.setAt(next, 2);
        }
    }

    public boolean isClickable(int i) {
        return this.getAt(i) == 0 && !this.isFinished();
    }

    public boolean isClicked(int i) {
        return this.getAt(i) != 0;
    }

    public String getIcon(int i) {
        if (this.getAt(i) == 1) {
            return "ui-icon-check";
        } else if (this.getAt(i) == 2) {
            return "ui-icon-radio-off";
        } else {
            return "";
        }
    }

    public boolean isFinished() {
        for (int i = 0; i < 3; i++) {
            if (this.getAt(i) == this.getAt(i + 3) && this.getAt(i) == this.getAt(i + 6) && this.getAt(i) != 0) {
                return true;
            }
            if (this.getAt(3 * i) == this.getAt(3 * i + 1) && this.getAt(i) == this.getAt(3 * i + 2) && this.getAt(i) != 0) {
                return true;
            }
        }
        if (this.getAt(0) == this.getAt(4) && this.getAt(0) == this.getAt(8) && this.getAt(0) != 0) {
            return true;
        }
        if (this.getAt(2) == this.getAt(4) && this.getAt(0) == this.getAt(6) && this.getAt(0) != 0) {
            return true;
        }
        if (this.getPlayable().size() == 0) {
            return true;
        }
        return false;
    }

    public int getNext() {
        List<Integer> playable = this.getPlayable();
        Collections.shuffle(playable);
        return playable.get(0);
    }

    public List<Integer> getPlayable() {
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < this.panel.length; i++) {
            if (this.panel[i] == 0) {
                lst.add(i);
            }
        }
        return lst;
    }
}
