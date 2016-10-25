package indi.mat.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import indi.mat.authenticator.AccountAuthenticator;

public class JavaMail {

	static String msgText = "This is a message body.\nHere's the second line.";
	
	public static void main(String[] args) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		Authenticator authenticator = new AccountAuthenticator("yangju90", "eWFuZ2p1");
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.debug", "true");
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(props,authenticator);
		Message message = new MimeMessage(session);
		// Set FROM:
		message.setFrom(new InternetAddress("yangju90@163.com"));
		// Set TO:
		InternetAddress a[] = new InternetAddress[1];
		a[0] = new InternetAddress("y583113758@163.com");
		message.setRecipients(Message.RecipientType.TO, a);
	    message.setSubject("JavaMail APIs Test");
	    message.setSentDate(new Date());
	    // If the desired charset is known, you can use
	    // setText(text, charset)
	    message.setText(msgText);
	    
	    // Send message
		Transport.send(message);
	}

}
