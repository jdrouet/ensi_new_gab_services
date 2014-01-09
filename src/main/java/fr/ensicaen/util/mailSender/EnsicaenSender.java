package fr.ensicaen.util.mailSender;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


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
	
	
	
	
	
	public void EnvoyerMail(String to,String messageHTML,File attachments) throws AddressException, MessagingException, IOException{
	
		   
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth","true");
			
			Session mailSession = Session.getDefaultInstance(props,null);
			mailSession.setDebug(sessionDebug);
		
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, true));
			msg.setSubject("Signature de document");
			msg.setSentDate(new Date());
			
		    

			 // le message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(messageHTML , "text/html");
	 
	        // les multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        
	        // ajouter attachments
	        
	           // for (int i=0;i<attachments.length;i++){ // pour joindre plusieurs fichiers
	            	System.out.println("source = "+attachments);
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(attachments);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	           // }
	       
	 
	       
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


	 public String ConstruireMessage(String name) throws MessagingException, IOException {
		   
		   
		 
		    StringBuilder messageComplet = new StringBuilder();
		    messageComplet.append("<HTML>");
		    messageComplet.append("<HEAD>");
		    messageComplet.append("<TITLE>");
		    messageComplet.append("Signature de document");
		    messageComplet.append("</TITLE>");
		    messageComplet.append("</HEAD>");
		  
		    
		    
		    
		    
		    messageComplet.append(" <body>"+
		    			"<table align=\"center\">"+
		    				"<tbody>"+
		    					"<tr style=\"border-color:#CCC; border-radius:3px;\">"+
		    						"<td colspan=\"3\" align=\"center\" style=\"background-image: linear-gradient(#fafafa, #e0e0e0);border-top-left-radius: 10px;border-top-right-radius: 10px;border: 2px solid #d8d8d8;\">"+ 	   
		    							"<h2 style=\"display: block;"+
		    									"font-size: 1.17em;"+
		    										"-webkit-margin-before: 1em;"+
		    										"-webkit-margin-after: 1em;"+
		    										"-webkit-margin-start: 0px;"+
		    										"-webkit-margin-end: 0px;"+
		    										"font-weight: bold;color:#18665f;margin-bottom:2px;\">"+
		    										"Signature de document <br />"+
		    										"</h2>"+ 
		    										"</td>"+ 
		    										"</tr>"+
		    										"<tr>"+
     
	 												"<td colspan=\"3\" style=\"text-align:left; background-color:#EFEFEF;background-image: linear-gradient(#fafafa, #e0e0e0);border-bottom-left-radius: 10px;border-bottom-right-radius: 10px;border: 2px solid #d8d8d8; \" >"+
	
	    
           												"<p style=\"font-size:15px;line-height:17px;color:#17365d;font-family:Century Gothic,sans-serif; margin-left:10px\">"+
        
               												" Bonjour,"+
               												"<br />"+
               												"<br />"+
               												"Monsieur <b>"+ name +"</b> vous a envoyé un document via le service de signature électronique <b>Diebold</b>. <br /><br />"+
               
                											"Afin d'éviter que nos e-mails soient bloqués par votre filtre de spam et de s'assurer que vous "+
                											"recevrez tous nos e-mails  nous vous proposons d'ajouter l'adresse suivante"+
                											"<a href=\"mailto:no-reply@GabServices.com\">no-reply@GabServices.com</a> dans votre carnet "+
                											"d'adresses.<br /><br />"+
                
                											"Cordialement,<br />"+
                											"L' équipe de Diebold </p>"+
                											"<hr style=\"width:100%;color:black;height:1px;\"/>"+
                											"</td>"+
                											"</tr>"+
		
       														"<tr>"+  
       
         													"<td style=\"padding: 4px 4px;\" align=\"left\">"+
         
         													"<img src=\"http://www.diebold.com/StyleLibrary/Diebold/img/logo-header.jpg\"/>"+
         
              	  
         													"</td>"+
          
          
         													"<td style=\"padding: 4px 4px;\" align=\"center\">"+
         
         													"<img src=\"http://greyc.stlo.unicaen.fr/fogrimmi/images/Ensicaen.gif\" width=\"115\" height=\"56\"/>"+
         
              	  
          													"</td>"+
          
          
         													"<td style=\"padding: 4px 4px;\" align=\"right\">"+
        
         													"<img src=\"http://911-2011.fr/wp/wp-content/uploads/2011/06/LogoUNICAEN-3.jpg\"/>"+
         
              	  
         													"</td>"+
          
    								"</tr>"+
    					 "</tbody>"+
    				"</table>"+
   
			"</body>");
		    

		    		
		    messageComplet.append("</HTML>");
		
		    return messageComplet.toString();
		  }
	 
	 
	 
	 public void  sendFile(String Sendername,String mailAddress, File file) throws AddressException, MessagingException, IOException {
          

        
          
          String messageenv = ConstruireMessage(Sendername);
          
          EnvoyerMail(mailAddress, messageenv,file);
          
	 }
}
