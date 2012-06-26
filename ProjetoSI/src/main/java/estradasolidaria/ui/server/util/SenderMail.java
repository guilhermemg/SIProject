package estradasolidaria.ui.server.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe responsavel por
 * enviar emails.
 * 
 * obs.: eh necessario ter um servidor de smtp disponivel 
 * (postfix por exemplo no linux) para rodar.
 * 
 */
public class SenderMail {
	/**
	 * Envia email da origem para
	 * o destino
	 * 
	 * @param origem: email de origem
	 * @param destino: email de destino
	 * @param text: texto do email
	 * @throws MessagingException
	 */
	public static void sendMail(String origem, String destino, String text) throws MessagingException {
	   
	   // Recipient's email ID needs to be mentioned.
	   String to = destino;
	
	   // Sender's email ID needs to be mentioned
	   String from = origem;
	
	   // Assuming you are sending email from localhost
	   String host = "localhost";
	
	   // Get system properties
	   Properties properties = System.getProperties();
	
	   // Setup mail server
	   properties.setProperty("mail.smtp.host", host);
	
	   // Get the default Session object.
	   Session session = Session.getDefaultInstance(properties);
	
	   try{
	      // Create a default MimeMessage object.
	      MimeMessage message = new MimeMessage(session);
	
	      // Set From: header field of the header.
	      message.setFrom(new InternetAddress(from));
	      
	      // Set To: header field of the header.
	      message.addRecipient(Message.RecipientType.TO,
	                               new InternetAddress(to));
	
	      // Set Subject: header field
	      message.setSubject("EstradaSolidaria Mail Service");
	
	      // Now set the actual message
	      message.setText(text);
	
	      // Send message
	      Transport.send(message);
	      //System.out.println("Sent message successfully...."); 
	      //TODO colocar em log depois, q email foi enviado com sucesso
	   }
	   catch (MessagingException mex) {
	      throw new MessagingException();
	   }
	}
}
