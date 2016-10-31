import java.awt.BorderLayout;
import java.awt.Dimension;

import indi.mat.client.main.Login;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login jf = new Login("邮件客户端");
		jf.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(new BorderLayout()); // 设置风格
		jf.drawLogin();
		jf.setSize(new Dimension(300, 200)); // 设置窗体大小
		jf.setLocationRelativeTo(null); // 在屏幕中间显示(居中显示)
		jf.setVisible(true);
	}

}
