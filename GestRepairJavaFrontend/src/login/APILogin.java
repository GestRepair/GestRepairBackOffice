/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import connect.Connect;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

/**
 *
 * @author Rui Barcelos
 */
public class APILogin {
    //Introduz ip para a classe Inteira
    Connect ip = new Connect();
    
    /**
     *
     * @param user
     * @param pass
     * @return senddata
     * @throws Exception
     */
    public String[] post(String user, String pass) throws Exception {
        //URL para a API
        URL url = new URL(ip.IP() + "/login");
        //Conexão à API
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //Tempo de leitura
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs
        //Metodo
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        //Headers
        byte[] message = (user + ":" + pass).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        //Cria um JSON objp e insere os dados a enviar
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("password", pass);
            objp.put("login", user);
            out.write(objp.toString());
            out.flush();
        }
        //res
        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String json = "";
        //insere linha a linha a string json
        while ((line = br.readLine()) != null) {
            json += line;
        }
        //Insere o objecto com o username e password e o JSON com o informação do utilizador
        String[] senddata = {objp.toString(), json};
        //Desliga a conexão desta class à API 
        connection.disconnect();
        //dados Retornados
        return senddata;
    }
}
