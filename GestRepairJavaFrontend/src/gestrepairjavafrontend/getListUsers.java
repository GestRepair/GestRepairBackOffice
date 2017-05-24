/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Convite
 */
public class getListUsers {


    public String getUL(String ip, String login) throws MalformedURLException, IOException, ParseException {
        
            URL url = new URL("http://"+ip+":8080/user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);//5 secs
            connection.setReadTimeout(5000);//5 secs
            
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            
            JSONObject newjson = (JSONObject)new JSONParser().parse(login);
            String user = newjson.get("login").toString();
            String pass = newjson.get("password").toString();
            byte[] message = (user+":"+pass).getBytes("UTF-8");
            String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
            connection.setRequestProperty("Authorization", "Basic "+encoded);
            
           //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            String json = "";
            
            while ((line = rd.readLine()) != null) {
              json += line;
              response.append(line);
              response.append('\r'); 
            }
            rd.close();  
            connection.disconnect();
            
            return json;
                       
    }
        
}