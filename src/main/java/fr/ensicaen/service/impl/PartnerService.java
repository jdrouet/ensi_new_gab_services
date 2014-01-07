/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.service.impl;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IPartnerService;

/**
 *
 * @author sofiene
 */
public class PartnerService implements IPartnerService {

    @Override
    public void transfer(Account partner, Account user, Float amount) {
        
        Operation operation =  new Operation();
        operation.setAmount(amount);
        operation.setSource(user);
        operation.setDestination(partner);
                         
    }
    
    
    
}
