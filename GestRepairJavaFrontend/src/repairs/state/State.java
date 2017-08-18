/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.state;

import connect.Connect;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class State extends Connect{
    public String GetStateList(String login) throws Exception {
        URL url = new URL(IP() +  "/repair/states" );
        HttpURLConnection connection = Conn(login, url, "GET");
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
    public String[][] ParseState(String list) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][2];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idstate") + "";
                dataTable[i][1] = (String) datas.get("nameState");
            }
            return dataTable;
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }
    public String[][] ShowState(String login) throws Exception {
        return ParseState(GetStateList(login));
    }
}
