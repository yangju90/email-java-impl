package indi.mat.read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import indi.mat.util.MySession;
import indi.mat.util.ReadFile;

/**
 * �����������ȡ����ڱ��ص��ʼ��ı�ź�����
 * 
 * @author mat
 *
 */
public class ReadMessage {
	


	public ReadMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vector<String> OutputHeader(){
		
		String string ="F:/download";
		
		Vector<String> vector = new Vector<String>();
		
		String[] str = ReadFile.getFileList(string);
		
		if(str !=null){
			for(int i = str.length-1;i>=0;i--){
				if(str[i].indexOf(".") != -1)continue;
				String temp = string + "/" + str[i];
				String tmp = getMessageHeader(temp);
				if(tmp!=null){
					vector.add(tmp);
				}
			}
		}else{
			System.out.println("�ż����!");
			return null;
		}
		return vector;
	}
	
	
	/**
	 * ̎���ż���Ϣͷ
	 * 
	 * @param s �ż���ŵ�λ��
	 * @return �ż�ͷ
	 */
	
	private String getMessageHeader(String s){
		
		String str = "";
		str += s.substring(s.lastIndexOf("/") +1,s.length());
	
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			MimeMessage mm;
			try {
				mm = new MimeMessage(MySession.getSession(),fis);
				InternetAddress[] address = (InternetAddress[]) mm.getFrom();
				
				for (InternetAddress ia : address) {

					String[] s1 = ia.toString().split(" ");

					str += " " +MimeUtility.decodeText(s1[0]);
					if (s1.length == 2) {
						str += " "+s1[1];
					}
				}
//				System.out.print("���⣺" + mm.getSubject()+"\n");

			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("��鿴"+s+"�ļ����Ƿ���һ���ʼ���");
				return null;
				
			} finally{
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}	

			return str;		
	}
}
