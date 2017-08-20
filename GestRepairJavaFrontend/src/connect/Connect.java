/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Convite
 */
public class Connect{
    public String IP(){
     return "http://127.0.0.1:8080";
    }
    public HttpURLConnection Conn(String login, URL url, String method) throws ParseException, IOException {
        HttpURLConnection connection;
        JSONObject newjson = (JSONObject) new JSONParser().parse(login);
        String user = newjson.get("login").toString();
        String pass = newjson.get("password").toString();
        byte[] message = (user + ":" + pass).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);

        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        return connection;
    }
    public String GETConnect (String login, URL url,String method) throws Exception {
        HttpURLConnection connection =Conn(login, url, method);
        //Get Response  
        InputStream is = connection.getInputStream();
        String json;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            json = "";
            while ((line = rd.readLine()) != null) {
                json += line;
                response.append(line);
                response.append('\r');
            }
        }
        connection.disconnect();
        return json;
    }
    public String SendConnect(String login, URL url, String method,JSONObject obj)throws Exception{
        HttpURLConnection connection =Conn(login, url,method);
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            out.write(obj.toString());
            out.flush();
        }
        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);  
        return (String) res.get("result");
    }
}
