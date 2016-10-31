package indi.mat.authenticator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AccountAuthenticator extends Authenticator {

	String name = "";
	String password = "";

	public AccountAuthenticator(String name, String password) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(name, password);
	}
	
	public static AccountAuthenticator getInstance(String name,String password){
		return new AccountAuthenticator(name ,password);
	}	
	
}
