package com.example.nhom11.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RestFBUtil {

	private static final String FACEBOOK_APP_ID = "2925420967573361";
	private static final String FACEBOOK_APP_SECRET = "43b9ce081d43ce0d27fb997418d328a7";
	private static final String FACEBOOK_REDIRECT_URL = "http://"+Server.IP+":"+Server.PORT+"/SmartPhoneStoreOnline/login-facebook";
	private static final String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

	public static String getToken(final String code) throws IOException {
	    String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
	    URL url=new URL(link);
	    URLConnection con=url.openConnection();	    
	    BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		String accessTokenJson="";
		String read=null;
		while((read=br.readLine())!=null) {
			accessTokenJson+=read;
		}	    
	    JsonObject json=new Gson().fromJson(accessTokenJson, JsonObject.class);
	    String accessToken=json.get("access_token").toString().replaceAll("\"", "");
	    return accessToken;
	  }
	
	public static String getUserInJson(String accessToken) 
			throws IOException {	// Tra ve chuoi Json voi Token da cho (Json gom id, name)		
		URL url=new URL("https://graph.facebook.com/me?fields=id,name,email&access_token="+accessToken);
		URLConnection con=url.openConnection();
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		String userJson="";
		String read=null;
		while((read=br.readLine())!=null) {
			userJson+=read;
		}
		return userJson;
		
	}
	
	
}
