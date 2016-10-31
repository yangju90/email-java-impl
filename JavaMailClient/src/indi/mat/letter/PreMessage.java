package indi.mat.letter;

import indi.mat.authenticator.AccountAuthenticator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class PreMessage {

	public static void main(String[] args) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		Session session = Session.getInstance(new Properties());
		MimeMessage mesg = new MimeMessage(session);

		Multipart multiPart = new MimeMultipart("multipart/mixed");
		mesg.setContent(multiPart);

		MimeBodyPart attach1 = new MimeBodyPart();
		MimeBodyPart attach2 = new MimeBodyPart();
		MimeBodyPart content = new MimeBodyPart();
		multiPart.addBodyPart(attach1);
		multiPart.addBodyPart(attach2);
		multiPart.addBodyPart(content);
		
		File file1 = new File("D:/’’∆¨ 07.jpg");
		attach1.attachFile(file1);
		File file2 = new File("D:/’’∆¨ 07.jpg");
		attach2.attachFile(file2);
		
		Multipart bodyMultiPart = new MimeMultipart("multipart/related");
		content.setContent(bodyMultiPart);
		
		MimeBodyPart gifImage = new MimeBodyPart();
		MimeBodyPart htmlWord = new MimeBodyPart();
		bodyMultiPart.addBodyPart(gifImage);
		bodyMultiPart.addBodyPart(htmlWord);
		
		File file3 = new File("D:/’’∆¨ 07.jpg");
		gifImage.attachFile(file3);
		htmlWord.setContent("123211", "type/html;charset='utf-8'");
		
		mesg.saveChanges();
		
		File ofile = new File("D:/email.html");
		OutputStream os = new FileOutputStream(ofile); 
		mesg.writeTo(os);
		os.close();
	}

}
