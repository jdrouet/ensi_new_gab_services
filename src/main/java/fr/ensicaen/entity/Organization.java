package fr.ensicaen.entity;

import java.io.Serializable;

import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author mhiri
 * @date 06/01/2014
 */
@Table(name = "organization")
public class Organization extends Client implements Serializable {
    private static final long serialVersionUID = 8240671633020666134L;


}
