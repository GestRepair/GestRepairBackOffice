/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

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
public abstract class Connect {

    /**
     * Aqui é definido o ip da API
     *
     * @return
     */
    public String IP() {
        return "http://ec2-52-56-143-158.eu-west-2.compute.amazonaws.com:8080";
    }

    /**
     * Aqui é feita a conexão à API
     * 
     * @param login
     * @param url
     * @param method
     * @return
     * @throws Exception
     */
    public HttpURLConnection Conn(String login, URL url, String method) throws Exception {
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
        connection.setRequestProperty("charset", "UTF-8");
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        return connection;
    }
    /**
     * Esta função é chamada caso se queira efectual um GET à API caso queira receber os dados
     * 
     * @param login
     * @param url
     * @param method
     * @return
     * @throws Exception 
     */
    public String GETConnect(String login, URL url, String method) throws Exception {
        HttpURLConnection connection = Conn(login, url, method);
        //Get Response  
        InputStream is = connection.getInputStream();
        String json;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
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
    /**
     * Faz um pedido à API enviando lhe parametros
     * @param login
     * @param url
     * @param method
     * @param obj
     * @return
     * @throws Exception 
     */
    public String[] SendConnect(String login, URL url, String method, JSONObject obj) throws Exception {
        HttpURLConnection connection = Conn(login, url, method);
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
            out.write(obj.toString());
            out.flush();
        }
        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        String val[] = new String[2];
        val[0] = (String) res.get("result");
        val[1] = (String) res.get("message");
        return val;
    }
    /**
     * Faz um pedido à API enviando lhe parametros e recebe uma reposta
     * @param login
     * @param url
     * @param method
     * @param obj
     * @return
     * @throws Exception 
     */
    public String[] SendConnectResp(String login, URL url, String method, JSONObject obj) throws Exception {
        HttpURLConnection connection = Conn(login, url, method);
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
            out.write(obj.toString());
            out.flush();
        }
        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        String val[] = new String[3];
        val[0] = (String) res.get("result");
        val[1] = (String) res.get("message");
        val[2] = (Object) res.get("data") + "";
        return val;
    }
}
