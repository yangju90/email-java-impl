package indi.mat.client.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientMain extends JFrame {

	JTextArea jta = null;

	public JTextArea getJta() {
		return jta;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}

	public ClientMain(String s) {
		super(s);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		centerComponent();
		setVisible(true);
	}

	public void centerComponent() {
		
		JScrollPane jspWest = new JScrollPane();
		JScrollPane jspCenter = new JScrollPane();
		jta = new JTextArea();
		
		String[] data = {"one", "two", "three", "four"};
		ListContents jl = new ListContents(data);
		
		JButton jb = new JButton("刷新");
		jb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] str = {"1","2","3"};
				jl.updateData(str);
			}
			
		});
		
//		JButton jb1 = new JButton("jb1");
//		JButton jb2 = new JButton("jb2");
//		JButton jb3 = new JButton("jb3");
//		JPanel jp1 = new JPanel();
//		jp1.setBackground(Color.red);
//		JPanel jp2 = new JPanel();
//		JPanel jp3 = new JPanel();


		
		// jp.add(jb1);
		// jp.add(jb2);
		// jp.add(jb3);

		jspWest.setViewportView(jl);
		jspCenter.setViewportView(jta);

		this.add(jspCenter, BorderLayout.CENTER); // 将textarea加入frame中
		this.add(jspWest, BorderLayout.WEST);
		this.add(jb, BorderLayout.SOUTH);
	}

}
