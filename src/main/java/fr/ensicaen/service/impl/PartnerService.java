/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.service.impl;

import fr.ensicaen.entity.Account;
import fr.ensicaen.entity.Operation;
import fr.ensicaen.service.IPartnerService;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author sofiene
 */
public class PartnerService implements IPartnerService {

    @Override
    public void transfer(Account partner, Account user, Float amount) {
        
        Operation operation =  new Operation();
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        operation.setAmount(amount);
        operation.setEvent(currentTimestamp);
        operation.setSource(user);
        operation.setDestination(partner);
                         
    }
    
    
    
}
