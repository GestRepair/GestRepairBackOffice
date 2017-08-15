/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.fuel;


import connect.Connect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Convite
 */
public class APIFuel extends Connect {

    public String getFuels(String login) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel");
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
        }
        connection.disconnect();
        return json;
    }
    public String[][] ListFuels(String list) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idFuel") + "";
                dataTable[i][1] = (String) datas.get("nameFuel");
            };
            return dataTable;

        } catch (ParseException pe) {
            System.out.println("Erro");
            return null;
        }
    }
    
    public String[][] Fuel(String login) throws IOException, ParseException, Exception {
        return ListFuels(getFuels(login));
    }

    public String[] InfoFuel(String login, int id) throws Exception{
        URL url = new URL(IP() + "/vehicle/fuel/"+id);
        HttpURLConnection connection = Conn(login, url, "GET");

        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[2];
        emp[0] = (long) newjsondata.get("idFuel") + "";
        emp[1] = (String) newjsondata.get("nameFuel");
        return emp;
    }
    public String PutFuel(String login, int id, String fuel) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel/" + id);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("nameFuel", fuel);
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
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        return (String) res.get("result");
    }
    public String PostFuel(String login, String fuel) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel");
        HttpURLConnection connection = Conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("nameFuel", fuel);
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
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        return (String) res.get("result");
    }
}
