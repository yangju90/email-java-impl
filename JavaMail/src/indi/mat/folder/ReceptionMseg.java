package indi.mat.folder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import indi.mat.authenticator.AccountAuthenticator;
import indi.mat.prase.PraseContent;
import indi.mat.search.DateSearch;



public class ReceptionMseg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateSearch dateSearch = new DateSearch();
		// Date date = new Date(116, 9, 24);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse("2016-10-24 00:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(date);
		 Authenticator authenticator = new AccountAuthenticator("",
		 "");
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", "pop3.163.com");
		props.put("mail.debug", "false");

		Session session = Session.getInstance(props, authenticator);
		session.setDebug(false);

		Store store = null;
		Folder folder = null;
		Message[] mesg = null;
		try {
			store = session.getStore("pop3");
			store.connect();
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			mesg = folder.search(dateSearch.pastToNow(date));
//			mesg = folder.getMessages();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Message m : mesg) {
			System.out.println("信件编号：" + m.getMessageNumber());
			System.out.print("信件来源：");
			try {
				InternetAddress[] address = (InternetAddress[]) m.getFrom();
				for (InternetAddress ia : address) {

					String[] s = ia.toString().split(" ");

					System.out.print(MimeUtility.decodeText(s[0]) + " ");
					if (s.length == 2) {
						System.out.print(s[1]);
					}

					System.out.print("\n");

				}
				System.out.print("主题：" + m.getSubject()+"\n");

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 解析正文
			PraseContent praseContent = PraseContent.getInstance((MimeMessage) m);
			praseContent.praseContent();
			
		}

		System.out.println("=======" + folder.getName());

		try {
			folder.close(false);
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
