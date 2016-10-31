package indi.mat.client.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import indi.mat.client.listener.LoginListener;

public class Login extends JFrame {

	public JButton getJb1() {
		return jb1;
	}

	public void setJb1(JButton jb1) {
		this.jb1 = jb1;
	}

	public JButton getJb2() {
		return jb2;
	}

	public void setJb2(JButton jb2) {
		this.jb2 = jb2;
	}

	public JTextField getTf1() {
		return tf1;
	}

	public void setTf1(JTextField tf1) {
		this.tf1 = tf1;
	}

	public JPasswordField getTf2() {
		return tf2;
	}

	public void setTf2(JPasswordField tf2) {
		this.tf2 = tf2;
	}

	LoginListener llistener = null;
	JButton jb1 = null;
	JButton jb2 = null;

	JTextField tf1 = null;
	JPasswordField tf2 = null;
	
	public Login(String string) {
		// TODO Auto-generated constructor stub
		super();
	}

	public void drawLogin() {

		llistener = new LoginListener(this);// 创建一个Listener，处理login中的请求

		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();
		JPanel j3 = new JPanel(new GridLayout(2, 1));
		JPanel j4 = new JPanel(new GridLayout(1, 2));
		JPanel j5 = new JPanel();

		JLabel l1 = new JLabel("账号：");
		JLabel l2 = new JLabel("密码：");

		tf1 = new JTextField(15);
		tf2 = new JPasswordField(15);

		jb1 = new JButton("连接");
		jb2 = new JButton("退出");
		jb1.addActionListener(llistener);
		jb2.addActionListener(llistener);

		j1.add(l1);
		j1.add(tf1);

		j2.add(l2);
		j2.add(tf2);

		j3.add(j1);
		j3.add(j2);

		j4.add(jb1);
		j4.add(jb2);

		this.getContentPane().add(j3, BorderLayout.CENTER);
		this.getContentPane().add(j5, BorderLayout.NORTH);

		j5.setPreferredSize(new Dimension(0, 30));

		this.getContentPane().add(j4, BorderLayout.SOUTH);
	}



}
