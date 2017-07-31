/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import budgets.*;
import ip.Connect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Rui Barcelos
 */
public class APISchedule {
    HttpURLConnection connection;
    Connect connect = new Connect();
    
    public void conn(String login, URL url, String method) throws ParseException, IOException {
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

    public String GetSchedule(String login,int id) throws MalformedURLException, IOException, ParseException {
        URL url;
        if(id == 0){
            url = new URL(connect.IP() + "/schedule/");
        }else{
            url = new URL(connect.IP() + "/schedule/"+id);
        }
        conn(login, url, "GET");
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
    @SuppressWarnings("empty-statement")
    public String[][] DataToTable(String list) throws java.text.ParseException {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][5];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idSchedule") + "";
                dataTable[i][1] = (String) datas.get("vehicle");
                dataTable[i][2] = (String) datas.get("service");
                dataTable[i][3] = (String) datas.get("date");
                dataTable[i][4] = (long) datas.get("isActive")+"";
            };
            return dataTable;
        } catch (ParseException pe) {
            return null;
        }
    }
    public String[][]ValuesToTable(String login,int id) throws IOException, ParseException, java.text.ParseException{
        return DataToTable(GetSchedule(login,id));
    } 

}
