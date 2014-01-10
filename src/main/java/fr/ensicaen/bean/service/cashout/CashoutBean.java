/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.ensicaen.bean.service.cashout;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Alexandre
 */

@ManagedBean
@SessionScoped
public class CashoutBean implements Serializable {
    
    List<Integer> amounts;
    
    public CashoutBean() {
        this.amounts = new ArrayList<>(Arrays.asList(10,20,40,60,80,100));
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }
    
    public void retirer(int amount) {
        
    }
    
}
