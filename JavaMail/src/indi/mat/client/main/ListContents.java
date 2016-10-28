package indi.mat.client.main;

import java.awt.Graphics;

import javax.swing.JList;

public class ListContents extends JList {	

	String[] str = null;
	
	public ListContents(String[] str){
		super(str);
		this.str = str;		
		setFixedCellWidth(100);
		setFixedCellHeight(50);
	}
	
	public void updateData(String[] str){
		setListData(str);
	}
}
