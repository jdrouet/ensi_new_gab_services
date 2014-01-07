package sncf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Alexandre
 */
public class SNCFformBean implements Serializable {
    
    public static final String PROP_DEPART = "depart";
    private String depart = "Caen";
    public static final String PROP_ARRIVEE = "arrivee";
    private String arrivee = "Paris";
    public static final String PROP_DATE = "date";
    private Date date;
    public static final String PROP_HEURE = "heure";
    private int heure;
    public static final String PROP_MINUTES = "minutes";
    private int minutes;
    
    private PropertyChangeSupport propertySupport;
    
    public SNCFformBean() throws ParseException {
        propertySupport = new PropertyChangeSupport(this);
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        this.date = new Date();
        //Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(this.date);   // assigns calendar to given date 
        this.heure = calendar.get(Calendar.HOUR_OF_DAY); 
        this.minutes = calendar.get(Calendar.MINUTE);
        //Date date1 = inputFormat.parse(today.toString());
        //date = outputFormat.format(date1);
        //dateFormat = new SimpleDateFormat("HH");
        //this.heure = dateFormat.format(date1);
        //dateFormat = new SimpleDateFormat("mm");
        //this.minutes = dateFormat.format(date1);
    }
    
    public String getDepart() {
        return depart;
    }
    
    public String getArrivee() {
        return arrivee;
    }
    
    public Date getDate() {
        return date;
    }
    
    public int getHeure() {
        return heure;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public void setDepart(String value) {
        String oldValue = depart;
        depart = value;
        propertySupport.firePropertyChange(PROP_DEPART, oldValue, depart);
    }
    
    public void setArrivee(String value) {
        String oldValue = arrivee;
        arrivee = value;
        propertySupport.firePropertyChange(PROP_ARRIVEE, oldValue, arrivee);
    }
    
    public void setDate(Date value) {
        Date oldValue = date;
        date = value;
        propertySupport.firePropertyChange(PROP_DATE, oldValue, date);
    }
    
    public void setHeure(int value) {
        int oldValue = heure;
        heure = value;
        propertySupport.firePropertyChange(PROP_HEURE, oldValue, heure);
    }
    
    public void setMinutes(int value) {
        int oldValue = minutes;
        minutes = value;
        propertySupport.firePropertyChange(PROP_MINUTES, oldValue, heure);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public List<String> completeVille(String query) {  
        List<String> results = new ArrayList<String>();
        return results;
    } 
    public List<String> completeHeure(String query) {  
        List<String> results = new ArrayList<String>();
        return results;  
    } 
    
    public void save() {
        
    }
}
