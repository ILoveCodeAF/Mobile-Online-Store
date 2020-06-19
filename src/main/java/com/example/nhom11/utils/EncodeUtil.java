package com.example.nhom11.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodeUtil {
	
	//Ma hoa 1 chuoi ky tu
	//Neu chuoi rong hoac null se tra ve null
	public static String encode(String str) {
		String code=null;
		if(str!=null && !str.isEmpty()) {
			code = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
		}
		return code;
	}
	
	//Neu dau vao rong -> Tra ve null
	public static String decode(String code) {
		String result=null;
		
		if(code!=null && !code.isEmpty()) {
			result=new String(Base64.getDecoder().decode(code), StandardCharsets.UTF_8);
		}
		return result;
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Hay nhap 1 chuoi: ");
		String str = reader.readLine();
		
		String encodedString, decodedString;
		encodedString = EncodeUtil.encode(str);
		decodedString = EncodeUtil.decode(encodedString);
		
		System.out.println("Encoded String: "+encodedString);
		System.out.println("Decoded String: "+decodedString);
		
	}
	
}
