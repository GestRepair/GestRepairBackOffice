/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public class Postlogin {


    public String [] post(String ip,String user, String pass) throws Exception{
        URL url = new URL("http://"+ip+":8080/login");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        byte[] message = (user+":"+pass).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        connection.setRequestProperty("Authorization", "Basic "+encoded);
        
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream()); 
        
        JSONObject objp = new JSONObject();
        objp.put("password",pass);
        objp.put("login",user);
        System.out.println(objp);
        out.write(objp.toString());
        out.flush();
        out.close();
        int res = connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String json = "";
        while((line = br.readLine() ) != null) {
            json += line;
        }
        String[] senddata = {objp.toString(),json.toString()};
        //System.out.println(json);
         connection.disconnect();
        return senddata;
    }
}
