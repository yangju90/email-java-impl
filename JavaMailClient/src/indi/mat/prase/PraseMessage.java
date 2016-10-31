package indi.mat.prase;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import indi.mat.search.DateSearch;

public class PraseMessage {

	private Folder folder = null;
	private Message[] mesgArray = null;


	public PraseMessage(Folder folder) {
		// TODO Auto-generated constructor stub
		this.folder = folder;
	}
	
	public PraseMessage(Message[] mesgArray) {
		// TODO Auto-generated constructor stub
		this.mesgArray = mesgArray;
	}

	public Vector<String> getHeader() {

		Vector<String> vector = new Vector<String>();
		
//		DateSearch dateSearch = new DateSearch();
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = null;
//		try {
//			date = sdf.parse("2016-10-24 00:00:00");
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		
//		Message[] mesgArray = null;
//		
//		try {
//			folder.open(Folder.READ_ONLY);
//			mesgArray = folder.search(dateSearch.pastToNow(date));
//		} catch (MessagingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}


		for (Message m : mesgArray) {

			String str = "";

			System.out.println("信件编号：" + m.getMessageNumber());

			str += m.getMessageNumber() + "|";

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
				System.out.print("主题：" + m.getSubject() + "\n");

				str += m.getSubject();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (str != null) {
				vector.add(str);
			}
		}

		return vector;
	}

}
