package indi.mat.folder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import indi.mat.search.DateSearch;
import indi.mat.util.DateIO;



public class ReceptionMseg {
	
	public ReceptionMseg() {

	}

	public void mailConnect(Session session,Date TimeIO) throws MessagingException {
		// TODO Auto-generated method stub
		
		DateSearch dateSearch = new DateSearch();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse("2016-10-24 00:00:00");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

//		 Authenticator authenticator = new AccountAuthenticator("xxxxx",
//		 "xxxxx");

		Store store = null;
		Folder folder = null;
		Message[] mesg = null;
		try {
			store = session.getStore("pop3");
			store.connect();
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			mesg = folder.search(dateSearch.pastToNow(date));
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		for (Message m : mesg) {
			int i = m.getMessageNumber();
			String str = "F:/download/"+i;
			try {
				FileOutputStream os = new FileOutputStream(str); 
				m.writeTo(os);
				
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			folder.close(false);
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
