package indi.mat.client.listener;

import indi.mat.client.main.ClientMain;
import indi.mat.client.main.Login;
import indi.mat.read.ReadMessage;
import indi.mat.util.CreateFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginListener implements ActionListener {

	Login jframe = null;

	public LoginListener(Login obj) {
		super();
		// TODO Auto-generated constructor stub
		this.jframe = obj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "连接") {
			if (jframe.getTf1().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入用户名", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (jframe.getTf2().getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "请输入密码", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			jframe.dispose();

			String is = jframe.getTf1().getText() + " " + jframe.getTf2().getText();
			CreateFile.overRideFileS(is, "F:/download/user.txt");

			ClientMain cm = new ClientMain("Mat邮箱");
			
			ReadMessage rm = new ReadMessage();
			cm.setWestList(rm.OutputHeader());

		} else if (e.getActionCommand() == "退出") {
			// jframe.dispose();
			System.exit(0);
		}
	}

}
