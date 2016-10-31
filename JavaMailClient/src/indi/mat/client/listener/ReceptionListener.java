package indi.mat.client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import indi.mat.client.main.ClientMain;
import indi.mat.folder.ReceptionMseg;
import indi.mat.read.ReadMessage;
import indi.mat.util.DateIO;
import indi.mat.util.MySession;

public class ReceptionListener implements ActionListener {
	
	ClientMain cm = null;

	public ReceptionListener(ClientMain cm) {
		super();
		// TODO Auto-generated constructor stub
		this.cm = cm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ReceptionMseg rm = new ReceptionMseg();
		try {
			rm.mailConnect(MySession.getSession(),DateIO.timeIO());
		} catch (AuthenticationFailedException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "账号密码错误！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ReadMessage rmesg = new ReadMessage();
		cm.setWestList(rmesg.OutputHeader());
	}

}
