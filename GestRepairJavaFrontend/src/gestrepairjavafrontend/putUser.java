/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Convite
 */
public class putUser {

    Ip ip = new Ip();

    /**
     *
     * @param login
     * @param numUser
     * @param name
     * @param street
     * @param zipCode
     * @param city
     * @param email
     * @param nif
     * @param contact
     * @throws Exception
     */
    public void putUsr(String login, String numUser, String name, String street, String zipCode, String city, String email, String nif, String contact) throws Exception {
        JSONObject newjson = (JSONObject) new JSONParser().parse(login);
        String user = newjson.get("login").toString();
        String pass = newjson.get("password").toString();
        byte[] message = (user + ":" + pass).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        
        URL url = new URL(ip.stIp() + "/user/" + numUser);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);

        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("nome", name);
            objp.put("morada", street);
            objp.put("codPostal", zipCode);
            objp.put("localidade", city);
            objp.put("email", email);
            objp.put("nif", nif);
            objp.put("contacto", contact);
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