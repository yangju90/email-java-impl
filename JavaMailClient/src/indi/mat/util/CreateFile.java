package indi.mat.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreateFile {
	
	public CreateFile(){
		
	}
	
	public static void overRideFileS(String is,String name){
		try {
			byte[] b = is.getBytes();
			
			FileOutputStream fos = new FileOutputStream(name);
			fos.write(b);
			
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
