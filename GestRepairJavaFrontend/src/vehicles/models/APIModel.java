/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.models;

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
 * @author Rui Barcelos
 */
public class APIModel extends Connect {
    
    /**
     * Car Model
     */
    /**
     * 
     * @param login
     * @param brand
     * @param model
     * @throws Exception 
     */
    public String PostModel(String login, int brand, String model) throws Exception {
        URL url = new URL(IP() + "/vehicle/model");
        HttpURLConnection connection = Conn(login, url, "POST");

        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("brand", brand);
            objp.put("model", model);
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
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        return (String) res.get("result");
    }
    /**
     * Get List Model
     * @param login
     * @param id
     * @return
     * @throws Exception 
     */
    private String GetModels(String login,int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/"+id+"/model/");
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
    @SuppressWarnings("empty-statement")
    private String[][] ListModels(String list) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idModel") + "";
                dataTable[i][1] = (String) datas.get("nameModel");
            };
            return dataTable;

        } catch (ParseException pe) {
            System.out.println("Erro");
            return null;
        }
    }
    /**
     * Put the data inside the array
     * @param login
     * @param id
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws Exception 
     */
    public String[][] Model(String login, int id) throws IOException, ParseException, Exception {
        return ListModels(GetModels(login,id));
    }
    public String[] InfoModel(String login, int id) throws Exception{
        URL url = new URL(IP() + "/vehicle/model/"+id);
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
        emp[0] = (long) newjsondata.get("idModel") + "";
        emp[1] = (String) newjsondata.get("nameModel");
        return emp;
    }
    /**
     * UPDATE model to database 
     * @param login
     * @param id
     * @param model
     * @return
     * @throws Exception 
     */
    public String PutModel(String login, int id, String model) throws Exception {
        URL url = new URL(IP() + "/vehicle/model/" + id);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("nameModel", model);
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
        String result = (String) res.get("result");
        return result;
    }
}
