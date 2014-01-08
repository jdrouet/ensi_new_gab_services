package fr.ensicaen.util.mailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


/**
 * @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
 */
public class EnsicaenSender {
    private String host;
    private String from;
    private boolean sessionDebug;

    public EnsicaenSender() {
        host = "smtp.ecole.ensicaen.fr";
        from = "no-reply@GabServices.com";
        sessionDebug = true;
    }


    public void sendMail(String to, String messageHTML, String sujet, File[] attachments) throws MessagingException, IOException {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");

        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(sessionDebug);

        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, true));
        msg.setSubject(sujet);
        msg.setSentDate(new Date());
        String messageenv = constructMessage(messageHTML, sujet, msg);


        // le message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(messageenv, "text/html");

        // les multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // ajouter attachments
        for (int i = 0; i < attachments.length; i++) {
            // System.out.println("source = " + attachments[i]);
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
        t.connect(host, user, pass);

        try {

            t.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Mail envoyé à : " + to);
            System.out.println(" de  " + from);


        } catch (javax.mail.SendFailedException e) {
            System.out.println("Le message n'a pas pu être envoyer.");

            System.out.println(e.getMessage());
        }

    }


    public String constructMessage(String contenu, String sujet, Message msg) {
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

    public static void sendFile(String mailAddress, String filename, File file) {

    }
}
