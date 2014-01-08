package fr.ensicaen.util.mailSender;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.*;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.util.*;



/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/


public class EnsicaenSender {

	
	private String host;
	private String from;
	private boolean sessionDebug;
	
	
	
	public EnsicaenSender(){
		
		host = "smtp.ecole.ensicaen.fr";
		from = "no-reply@GabServices.com";
		sessionDebug = true;
		
		
	}
	
	
	
	
	
	public void EnvoyerMail(String to,String messageHTML,String sujet,String[] attachments) throws AddressException, MessagingException, IOException{
	
		   
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth","true");
			
			Session mailSession = Session.getDefaultInstance(props,null);
			mailSession.setDebug(sessionDebug);
		
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, true));
			msg.setSubject(sujet);
			msg.setSentDate(new Date());
			String messageenv = ConstruireMessage(messageHTML, sujet,msg);
		    

			 // le message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(messageenv , "text/html");
	 
	        // les multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        
	        // ajouter attachments
	        
	            for (int i=0;i<attachments.length;i++){
	            	System.out.println("source = "+attachments[i]);
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(attachments[i]);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	       
	 
	       
	        msg.setContent(multipart);
	
			
			
			String user = "adee";
			String pass = "0406/adee"; 
			Transport t = mailSession.getTransport("smtp");
			t.connect(host,user, pass);
		
			try{
				
				t.sendMessage(msg, msg.getAllRecipients());
				System.out.println("Mail envoyé à : " + to);
				System.out.println(" de  " + from);
				
				
			}catch( javax.mail.SendFailedException e){
				System.out.println("Le message n'a pas pu être envoyer.");
				
				System.out.println(e.getMessage());
			}
	
	}


	 public String ConstruireMessage(String contenu, String sujet,Message msg) throws MessagingException, IOException {
		   
		    StringBuilder messageComplet = new StringBuilder();
		    messageComplet.append("<HTML>");
		    messageComplet.append("<HEAD>");
		    messageComplet.append("<TITLE>");
		    messageComplet.append(sujet + "");
		    messageComplet.append("</TITLE>");
		    messageComplet.append("</HEAD>");
		  
		    messageComplet.append("<BODY>");
		    messageComplet.append("<H3>" + sujet + "</H3>" + "");
		    
		    messageComplet.append(contenu);
		    messageComplet.append("");
		 
		    messageComplet.append("</BODY>");
		    messageComplet.append("</HTML>");
		
		    return messageComplet.toString();
		  }
}
