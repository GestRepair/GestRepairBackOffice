/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import ip.Connect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public class APIUsers {
    Connect connect = new Connect();
    HttpURLConnection connection;
    public void conn(String login,URL url,String method) throws ParseException, IOException{
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
    }
    public void PostEmployer(String login, int idUser, int service) throws Exception {
        URL url = new URL(connect.IP() + "/user/employer");
        conn(login,url,"POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("user", idUser);
            objp.put("service", service);
            out.write(objp.toString());
            out.flush();
        }
        int res = connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        System.out.println(json);
        connection.disconnect();
    }
    public void PostUsr(String login, String name, String street, String zipCode, String city, String email, String nif, String contact,String username) throws Exception {
        URL url = new URL(connect.IP() + "/user");
        conn(login,url,"POST");

        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("name", name);
            objp.put("street", street);
            objp.put("zipcode", zipCode);
            objp.put("city", city);
            objp.put("email", email);
            objp.put("nif", nif);
            objp.put("contact", contact);
            objp.put("username", username);
            objp.put("password", nif);
            out.write(objp.toString());
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
        System.out.println(json);
        connection.disconnect();
    }
    public String getUL(String login,int id) throws MalformedURLException, IOException, ParseException {
        URL url;
        
        if (id==2) {
            url = new URL(connect.IP() + "/user");
        }else{
            url = new URL(connect.IP() + "/user/type/"+id);
        }
        conn(login,url,"GET");
        
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
        } // or StringBuffer if Java version 5+
        connection.disconnect();
        return json;
    }
    public void putUsr(String login, String numUser, String name, String street, String zipCode, String city, String email, String nif, String contact) throws Exception {
        URL url = new URL(connect.IP() + "/user/" + numUser);
        conn(login,url,"PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("name", name);
            objp.put("street", street);
            objp.put("zipcode", zipCode);
            objp.put("city", city);
            objp.put("email", email);
            objp.put("nif", nif);
            objp.put("contact", contact);
            out.write(objp.toString());
            out.flush();
        }
        int res = connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        System.out.println(json);
        connection.disconnect();
    }
}
