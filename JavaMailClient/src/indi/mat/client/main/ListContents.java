package indi.mat.client.main;

import java.util.Vector;

import javax.swing.JList;

public class ListContents extends JList {	
	
	public ListContents(){
		super();
		setFixedCellWidth(200);
		setFixedCellHeight(50);
	}
	
	public void updateData(Vector<String> str){
		setListData(str);
	}
}
