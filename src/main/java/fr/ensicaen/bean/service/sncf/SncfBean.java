package fr.ensicaen.bean.service.sncf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Jérémie Drouet
 * Date: 07/01/14
 */
@ManagedBean
@SessionScoped
public class SncfBean implements Serializable {
    private static final long serialVersionUID = -4134614781118798187L;

    private String source;
    private String destination;
    private Date start;

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
}
