package fr.ensicaen.util.mailSender;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/


public class MainEnsicaen {

	
	


    public static void main(String[] args) throws AddressException, MessagingException, IOException {
    	
    	
       EnsicaenSender mail = new EnsicaenSender();
        
    // attachments
       String[] attachments = new String[3];
       attachments[0] = "C:/DP1.pdf";
       attachments[1] = "C:/Corba-conception-p21.pdf";
       attachments[2] = "C:/Corba-conception-p22.pdf";
       
        String to ="yassir.bouhaddaoui@gmail.com" ; 
        String sujet = "sujet : balance de l'HTML CSS ici";
        String messageHTML = "contenu :balance de l'HTML CSS ici";

        mail.EnvoyerMail(to, messageHTML, sujet,attachments);
      }
  
    
    
}
