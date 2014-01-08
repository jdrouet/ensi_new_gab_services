/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.ensicaen.bean.service.sncf;

import java.beans.*;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Alexandre
 */
@ManagedBean
@RequestScoped
public class Voyage implements Serializable {
    
    private String source;
    private String destination;
    private String date;
    private String heure;
    private String type;
    private Float prix;
    private int numero;

    public Voyage() {
        
    }
    
    public Voyage(String source, String destination, String date, String heure, String type, Float prix, int numero) {
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.heure = heure;
        this.type = type;
        this.prix = prix;
        this.numero = numero;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
