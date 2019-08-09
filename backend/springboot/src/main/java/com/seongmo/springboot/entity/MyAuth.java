package com.seongmo.springboot.entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAuth  {
    private static final String USER_AGENT = "Chrome";

    public static void sendGet() throws Exception {
        URL url = new URL("https://accounts.google.com/o/oauth2/auth?response_type=token&client_id=527574403487-5rjveiqpfsptnn22qge8ohmkl3p3obmi.apps.googleusercontent.com&redirect_uris=http://www.google.com&scope=https://www.googleapis.com/auth/analytics.readonly&redirect_uri=http://www.google.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("HTTP 응답 코드 : " + responseCode);
        System.out.println("HTTP body : " + response.toString());

    }

}
