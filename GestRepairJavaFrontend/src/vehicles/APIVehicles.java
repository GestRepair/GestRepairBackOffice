/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import ip.Connect;
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
public class APIVehicles {

    HttpURLConnection connection;

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
    Connect connect = new Connect();

    public void PostBrand(String login, String brand) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/brand");
        conn(login, url, "POST");
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
        System.out.println(json);
        connection.disconnect();
    }
    
    public void POSTAddVehicle(String login,int id,int model,String registration,int fuel,String horsepower,String displacement,String kilometers,String fronttiresize,String reartiresize,String date) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/"+id+"/desk");
        conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("model", model);
            objp.put("registration", registration);
            objp.put("fuel", fuel);
            objp.put("horsepower", horsepower);
            objp.put("displacement", displacement);
            objp.put("kilometers", kilometers);
            objp.put("fronttiresize", fronttiresize);
            objp.put("reartiresize", reartiresize);
            objp.put("date", date);
            out.write(objp.toString());
            out.flush();
        }
        connection.getResponseCode();
        System.out.println(objp);
        InputStream is = connection.getInputStream();
        System.out.println(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        System.out.println(json);
        connection.disconnect();
    }

    private String GetBrands(String login) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/brand");
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
    public String[][] Brand(String login) throws IOException, ParseException, Exception {
        return ListBrand(GetBrands(login));
    }
    public String[] InfoBrand(String login, int id) throws Exception{
        URL url = new URL(connect.IP() + "/vehicle/brand/"+id);
        conn(login, url, "GET");

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
    public String GetModels(String login,int id) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/"+id+"/model/");
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
    public String[][] ListModels(String list) {
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
    public String[][] Model(String login, int id) throws IOException, ParseException, Exception {
        return ListModels(GetModels(login,id));
    }
    public String getFuels(String login) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/fuel");
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

    public void PostModel(String login, int brand, String model) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/model");
        conn(login, url, "POST");

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
    }

    public String GetVehicles(String login, int id) throws MalformedURLException, IOException, ParseException {
        URL url;
        if (id == 0) {
            url = new URL(connect.IP() + "/vehicle/");
        } else {
            url = new URL(connect.IP() + "/vehicle/" + id + "/user");
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
        } // or StringBuffer if Java version 5+
        connection.disconnect();
        return json;
    }

    public void PutVehicle(String login, String id, String registration, String horsepower, String displacement, String kilometers, String fronttiresize, String reartiresize) throws Exception {
        URL url = new URL(connect.IP() + "/vehicle/" + id);
        conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("registration", registration);
            objp.put("horsepower", horsepower);
            objp.put("kilometers", kilometers);
            objp.put("displacement", displacement);
            objp.put("fronttiresize", fronttiresize);
            objp.put("reartiresize", reartiresize);
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
