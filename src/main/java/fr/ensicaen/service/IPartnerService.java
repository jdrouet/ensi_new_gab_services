/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.service;

import fr.ensicaen.entity.Account;

/**
 *
 * @author sofiene
 */
public interface IPartnerService {
    
    public void transfer (Account partner, Account user, Float amount);
    
}
