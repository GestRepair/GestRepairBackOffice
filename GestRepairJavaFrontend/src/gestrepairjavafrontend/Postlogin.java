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

import org.json.simple.JSONObject;

/**
 *
 * @author Rui Barcelos
 */
public class Postlogin {


    public void post(String ip,String user, String pass) throws Exception{
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
        
        JSONObject obj = new JSONObject();
        obj.put("password",user);
        obj.put("login",pass);
        System.out.println(obj);
        out.write(obj.toString());
        out.flush();
        out.close();
        int res = connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while((line = br.readLine() ) != null) {
            System.out.println(line);
        }
        connection.disconnect();
    }
}
