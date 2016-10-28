package indi.mat.letter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import indi.mat.authenticator.AccountAuthenticator;

public class Letter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Authenticator authenticator = new AccountAuthenticator("yangju90", "eWFuZ2p1");
//		Authenticator authenticator = new AccountAuthenticator("yangju90", "y87103546");

		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.debug", "true");
//		prop.setProperty("mail.host", "smtp.sina.com");
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(prop,authenticator);
		MimeMessage mesg = new MimeMessage(session);

		try {
			mesg.setReplyTo(new InternetAddress[]{new InternetAddress("yangju90@sina.com")});
			
			mesg.setSubject("风情同志，请认真高清楚里面的内容！");
			// Set FROM:
//			InternetAddress ina = new InternetAddress("yangju90@163.com");
//			ina.setPersonal("小当家");
//			mesg.setFrom(ina);
			mesg.setFrom(new InternetAddress(""+MimeUtility.encodeText("英航")+" <yangju90@163.com>"));
			// Set TO:
			InternetAddress a[] = new InternetAddress[1];
			a[0] = new InternetAddress("851232695@qq.com");
			a[0].setPersonal("杨举");;
			mesg.setRecipients(Message.RecipientType.TO, a);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MimeMultipart letterMp = new MimeMultipart("mixed");
		try {
			mesg.setContent(letterMp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MimeBodyPart attach1 = new MimeBodyPart();
		MimeBodyPart attach2 = new MimeBodyPart();
		MimeBodyPart content = new MimeBodyPart();
		try {
			letterMp.addBodyPart(content);
			letterMp.addBodyPart(attach1);
//			letterMp.addBodyPart(attach2);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file1 = new File("E:/IP1.mat");
		File file2 = new File("E:/testph.jpg");
		try {
			attach1.attachFile(file1);
//			attach1.setFileName("xczqdb.ppt");
			attach2.attachFile(file2);
//			attach2.setFileName("p1.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MimeMultipart bodyMp = new MimeMultipart("related");
		try {
			content.setContent(bodyMp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MimeBodyPart image = new MimeBodyPart();
		MimeBodyPart letterword = new MimeBodyPart();

		try {
			bodyMp.addBodyPart(image);
			bodyMp.addBodyPart(letterword);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DataSource ds = new FileDataSource("E:/testph.jpg");
		DataHandler dh = new DataHandler(ds);
		try {
			image.setDataHandler(dh);
			image.setHeader("Content-ID", "<123>");
			image.setDescription("INLINE");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			letterword.setContent("<font color='red'>东邪西毒，全身同名不留情</font><img src='cid:123'></img>","text/html;charset=gbk");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			mesg.saveChanges();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("更改错误！");
		}
		
		
//		try {
//			Transport.send(mesg);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		File wirteOut = new File("E:/IP1.mat");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(wirteOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		try {
			mesg.writeTo(os);
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
