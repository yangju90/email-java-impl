import java.awt.BorderLayout;
import java.awt.Dimension;

import indi.mat.client.main.Login;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login jf = new Login("�ʼ��ͻ���");
		jf.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(new BorderLayout()); // ���÷��
		jf.drawLogin();
		jf.setSize(new Dimension(300, 200)); // ���ô����С
		jf.setLocationRelativeTo(null); // ����Ļ�м���ʾ(������ʾ)
		jf.setVisible(true);
	}

}
