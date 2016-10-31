package indi.mat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class DateIO {

	public DateIO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 缺陷，不想写
	 * 
	 * 当账号密码不正确，点击刷新时间会写入文件中，但是信件不能从服务器中跟新下来
	 * @return
	 */
	
	public static Date timeIO(){
		
		File file =new File("F:/download/time.txt");
		Date date = null;
		
		if(!file.exists()){
			date = new Date();
			
			Long li = date.getTime(); 
			byte[] bi = li.toString().getBytes();
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(bi);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				byte[] b = new byte[1024];
				FileInputStream fis = new FileInputStream(file);
				fis.read(b);
				fis.close();
				long l = Long.parseLong(new String(b,"gbk").trim());
				date = new Date();
				
				Long li = date.getTime(); 
				byte[] bi = li.toString().getBytes();
				try {
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(bi);
					fos.flush();
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				date = new Date(l);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println(date);
		return date;
		
	}
	
}
