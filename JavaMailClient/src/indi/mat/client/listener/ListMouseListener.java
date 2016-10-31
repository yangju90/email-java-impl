package indi.mat.client.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import indi.mat.client.main.ClientMain;
import indi.mat.prase.PraseContent;
import indi.mat.util.MySession;

/**
 * 
 * 关于List列表事件监听，实现了单击和双击的判断
 * 
 * @author mat
 *
 */

public class ListMouseListener implements MouseListener {

	ClientMain cm = null;
	private static boolean flag = false;
	private static int clickNum = 0;

	public ListMouseListener(ClientMain cm) {
		super();
		// TODO Auto-generated constructor stub
		this.cm = cm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		final MouseEvent me = e;
		flag = false;

		if (this.clickNum == 1) {
			this.mouseDoubleClick(me);
			this.flag = true;
			this.clickNum = 0;
			return;
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			private int n = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(flag){
					clickNum = 0;
					this.cancel();
					return;
				}
				if(n==1){
					mouseSingleClick(me);
					clickNum = 0;
					this.cancel();
					return;
				}
				clickNum ++;
				n++;
			}
			
		}, new Date(), 500);

	}

	private void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		String s = cm.getJl().getSelectedValue().toString();
		String[] strArray = s.split(" ");
		String string ="F:/download/" + strArray[0];
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(string);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MimeMessage mm;
		try {
			mm = new MimeMessage(MySession.getSession(),fis);
			PraseContent.getInstance(mm,cm);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("请查看"+s+"文件夹是否是一封邮件！");
			
		} finally{
			try {
				fis.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}	
	}
	
	private void mouseSingleClick(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("SingleClick");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("release");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("enter");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("exit");

	}

}
