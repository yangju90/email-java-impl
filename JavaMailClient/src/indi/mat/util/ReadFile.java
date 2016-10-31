package indi.mat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import indi.mat.authenticator.AccountAuthenticator;

public class ReadFile {

	public ReadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * �����ļ��У������ļ������ż�������
	 * 
	 * @param s �ļ��еľ��Ե�ַ
	 * @return �ļ����ڵ��ļ���
	 */
	
	public static String[] getFileList(String s) {
		String[] str = null;
		File file = new File(s);
		
		if (file.isDirectory()) {
			str = file.list();
		}else {
			System.out.println(s+"��һ���ļ�");
			return null;
		}
		
		return str;
	}
	
	/**
	 * ���ļ��ж�ȡ�û���������
	 * 
	 * @return
	 */
	public static AccountAuthenticator getAuth(){
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("F:/download/user.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] b = new byte[1024];
		try {
			int i = fis.read(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new String(b);
		String[] strNP = str.toString().split(" ");
		return AccountAuthenticator.getInstance(strNP[0], strNP[1]);

	}
}
