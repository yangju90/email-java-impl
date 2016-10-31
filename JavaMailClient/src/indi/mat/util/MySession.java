package indi.mat.util;

import java.util.Properties;

import javax.mail.Session;

public class MySession {
	
	public static Session getSession(){
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", "pop3.163.com");
		props.put("mail.debug", "false");

		Session session = Session.getInstance(props, ReadFile.getAuth());
		session.setDebug(false);
		return session;
	}
}
