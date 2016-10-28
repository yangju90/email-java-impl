package indi.mat.mail;

import indi.mat.authenticator.AccountAuthenticator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//��д������Ϣ
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.debug", "true");
		props.setProperty("mail.host", "smtp.sina.com");
		props.setProperty("mail.smtp.auth", "true");
		
		//�����û�������
		Session session = Session.getInstance(props,
				AccountAuthenticator.getInstance("", ""));
		
		FileInputStream is = null;
		MimeMessage mesg = null;
		
		//��ȡҪ���͵��ļ�
		try {
			is = new FileInputStream("E:/IP1.mat");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��װ�ŷ�
		try {
			mesg = new MimeMessage(session, is);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�����ż�
		try {
			Transport.send(mesg, InternetAddress.parse(""));//��д�����ż���Ŀ��
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
