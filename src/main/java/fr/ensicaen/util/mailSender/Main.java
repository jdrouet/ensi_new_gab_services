package fr.ensicaen.util.mailSender;


import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Main {
	
	
	
	 public static void main(String[] args) throws AddressException, MessagingException, IOException {
	    	
	    	
         EnsicaenSender mail = new EnsicaenSender();
           
          
          mail.sendFile("Sender Namer","YOURemail", new File("C:/DP1.pdf"));
        
      }

}
