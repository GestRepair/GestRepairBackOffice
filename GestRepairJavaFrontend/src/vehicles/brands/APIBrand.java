/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.brands;

import connect.Connect;
import java.io.BufferedReader;
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
public class APIBrand extends Connect {
    /**
     * BRAND
     */
    /**
     * POST BRAND
     * ADD to database new Brand
     * @param login
     * @param brand
     * @return 
     * @throws Exception 
     */
    public String PostBrand(String login, String brand) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand");
        HttpURLConnection connection = Conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("brand", brand);
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
    /**
     * GET Brand List
     * @param login
     * @return
     * @throws Exception 
     */
    private String GetBrands(String login) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand");
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
    /**
     * Convert list to Array Two Dimensional
     * @param list
     * @return 
     */
    private String[][] ListBrand(String list) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idBrand") + "";
                dataTable[i][1] = (String) datas.get("nameBrand");
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
     * @return
     * @throws Exception 
     */
    public String[][] Brand(String login) throws Exception {
        return ListBrand(GetBrands(login));
    }
    /**
     * GET Info data on user
     * @param login
     * @param id
     * @return
     * @throws Exception 
     */
    public String[] InfoBrand(String login, int id) throws Exception{
        URL url = new URL(IP() + "/vehicle/brand/"+id);
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
        emp[0] = (long) newjsondata.get("idBrand") + "";
        emp[1] = (String) newjsondata.get("nameBrand");
        return emp;
    }
    /**
     * UPDATE Brand to Database
     * @param login
     * @param id
     * @param brand
     * @return
     * @throws Exception 
     */
    public String PutBrand(String login, int id, String brand) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand/" + id);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("nameBrand", brand);
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
