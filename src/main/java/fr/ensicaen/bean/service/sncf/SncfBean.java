package fr.ensicaen.bean.service.sncf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class SncfBean implements Serializable {
    
    public class Voyage {
        private String source;

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
        private String destination;
        private String date;
        private String heure;
        private String type;
        
        public Voyage() {
            
        }
        
        public Voyage(String source, String destination, String date, String heure, String type) {
            this.source = source;
            this.destination = destination;
            this.date = date;
            this.heure = heure;
            this.type = type;
        }
        
        
    }
    
    private static final long serialVersionUID = -4134614781118798187L;

    private String source;
    private String destination;
    private Date start = new Date();
    private List<Voyage> voyages;
    private Voyage selectedVoyage;
    
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    
    public List<Voyage> getVoyages() {
        return voyages;
    }
    
    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }
    
    public Voyage getSelectedVoyage() {
        return this.selectedVoyage;
    }
    
    public void setSelectedVoyage(Voyage voyage) {
        this.selectedVoyage = voyage;
    }
    
    public void rechercherVoyages() {
        int[] minutes = {10,36,55};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        DateFormat timeFormat = new SimpleDateFormat("hh");
        for(int i=0; i<6; i++) {
            Voyage v = new Voyage(this.source, this.destination, dateFormat.format(this.start) , timeFormat.format(this.start)+minutes[i%3], "Intercité");
            this.voyages.add(v);
        }
    }
}
