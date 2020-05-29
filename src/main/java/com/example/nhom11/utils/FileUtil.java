package com.example.nhom11.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.Part;

public class FileUtil {
	
	//Upload file + Tra ve ket qua Upload:
	//Thanh cong | That bai | File qua dung luong | File trong
	public static String upload(Part part, String folderLocation, long maxUploadSize) {
		String notification = "Upload Fail";
		
		File f=new File(folderLocation);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		if(part==null || part.getSize()==0) {
			notification = "You haven't select any File"; 
		}
		else if(part.getSize()>maxUploadSize) {
			notification = "File break the Upload size limit";
		}
		else {
			
			try {
				//Doc file
				BufferedInputStream bis=
						new BufferedInputStream(part.getInputStream());
				byte[] data = new byte[(int)part.getSize()];
				bis.read(data);
				//Ghi File
				File file=new File(folderLocation+File.separator+getNewFileName(part.getSubmittedFileName()));
				BufferedOutputStream bos=
						new BufferedOutputStream(new FileOutputStream(file));
				bos.write(data);
				
				bos.close();
				bis.close();
				
				notification = "Upload Success";
				
			} catch (IOException e) {
				notification = "Upload Fail";
			}
		}
		
		return notification;
	}
	
	
	
	private static String getNewFileName(String originalFileName) {
		return System.currentTimeMillis()+originalFileName.substring(originalFileName.lastIndexOf("."));
	}

}
