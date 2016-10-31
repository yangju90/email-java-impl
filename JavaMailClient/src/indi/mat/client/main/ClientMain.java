package indi.mat.client.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import indi.mat.client.listener.ListMouseListener;
import indi.mat.client.listener.ReceptionListener;

public class ClientMain extends JFrame {
	
	JEditorPane jep = null;
	JTextArea jta = null;
	JScrollPane jspWest = null;
	JScrollPane jspCenter = null;
	ListContents jl = null;

 	public JEditorPane getJep() {
		return jep;
	}

	public void setJep(JEditorPane jep) {
		this.jep = jep;
	}

	
	public ListContents getJl() {
		return jl;
	}

	public void setJl(ListContents jl) {
		this.jl = jl;
	}


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
		setVisible(true);
		centerComponent();
	}
	

	public void centerComponent() {
		
		jspWest = new JScrollPane();
		jl = new ListContents();
//		jl.addListSelectionListener(new ListListener(this));
		jl.addMouseListener(new ListMouseListener(this));
		
		JScrollPane jspCenter = new JScrollPane();
		jta = new JTextArea();
		jep = new JEditorPane();

		
		JPanel jSouth = new JPanel();
		//�����ť��ˢ�½����ż�
		JButton jb = new JButton("ˢ��");
		jb.addActionListener(new ReceptionListener(this));
		
		jSouth.add(jb);
		
		jspCenter.setViewportView(jep);
		jspWest.setViewportView(jl);

		this.add(jspCenter, BorderLayout.CENTER); // ��textarea����frame��
		this.add(jspWest, BorderLayout.WEST);
		this.add(jSouth, BorderLayout.NORTH);
	}
	
	
	public void setWestList(Vector<String> vector){
		jl.updateData(vector);
	}
}
