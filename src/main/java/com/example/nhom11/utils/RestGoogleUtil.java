package com.example.nhom11.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.nhom11.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RestGoogleUtil {

	private static final String GOOGLE_CLIENT_ID = "660596394116-n5kd2jlllnt70f0al7r40ncjmvi9kum5.apps.googleusercontent.com";
	private static final String GOOGLE_KEY_SECRET = "mcbQ3cWrooAs5Z-3Dkw6UJ-o";
	private static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/SmartPhoneStoreOnline/login-google";
	private static final String GOOGLE_LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";
	private static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo";
	private static final String GOOGLE_GRANT_TYPE = "authorization_code";

	public static String getToken(String code) throws IOException {

		String params = "client_secret=" + GOOGLE_KEY_SECRET;
		params += "&client_id=" + GOOGLE_CLIENT_ID;
		params += "&redirect_uri=" + GOOGLE_REDIRECT_URI;
		params += "&code=" + code;
		params += "&grant_type=" + GOOGLE_GRANT_TYPE;

		URL url = new URL(GOOGLE_LINK_GET_TOKEN);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setDoOutput(true);
		con.setRequestMethod("POST");

		OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());// send POST reuest
		os.write(params);
		os.flush();
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		String accessTokenJson="";
		String read=null;
		while((read=br.readLine())!=null) {
			accessTokenJson+=read;
		}

		JsonObject jobj = new Gson().fromJson(accessTokenJson, JsonObject.class);// Extract Access Token
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

		os.close();
		br.close();
		return accessToken;
	}

	public static User getUser(String accessToken) throws IOException {
        URL url = new URL(GOOGLE_LINK_GET_USER_INFO + "?access_token=" + accessToken);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String userJson = "";
        String read;
        while ((read = br.readLine()) != null) {
            userJson += read;
        }
        return new Gson().fromJson(userJson, User.class);

    }

}
