/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.vehicles;

import connect.Connect;
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
public class APIVehicles extends Connect {
    /**
     * VEHICLE
     */
    /**
     * POST Vehicle
     * ADD Vehicle to database
     * @param login
     * @param id
     * @param data
     * @return 
     * @throws Exception 
     */
    public String POSTAddVehicle(String login,int id,String[] data) throws Exception {
        URL url = new URL(IP() + "/vehicle/"+id+"/desk");
        HttpURLConnection connection = Conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("model", data[0]);
            objp.put("registration", data[1]);
            objp.put("fuel", data[2]);
            objp.put("horsepower", data[3]);
            objp.put("displacement", data[4]);
            objp.put("kilometers", data[5]);
            objp.put("fronttiresize", data[6]);
            objp.put("reartiresize", data[7]);
            objp.put("date", data[8]);
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
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        return (String) res.get("result");
    }

    public String GetVehicles(String login, int id) throws MalformedURLException, IOException, ParseException {
        URL url;
        if (id == 0) {
            url = new URL(IP() + "/vehicle/");
        } else {
            url = new URL(IP() + "/vehicle/" + id + "/user");
        }
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
    private String[][] ParseVehicles(String list, int id) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][(id==0)?12:11];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idVehicle") + "";
                dataTable[i][1] = (String) datas.get("registration");
                dataTable[i][2] = (String) datas.get("nameBrand");
                dataTable[i][3] = (String) datas.get("nameModel");
                dataTable[i][4] = (long) datas.get("horsepower")+ "";
                dataTable[i][5] = (long) datas.get("displacement")+ "";
                dataTable[i][6] = (long) datas.get("kilometers")+ "";
                dataTable[i][7] = (String) datas.get("nameFuel");
                dataTable[i][8] = (String) datas.get("fronttiresize");
                dataTable[i][9] = (String) datas.get("reartiresize");
                dataTable[i][10] = ((String) datas.get("date")).substring(0,10);  
                if(id==0){
                    dataTable[i][11] = (String) datas.get("username")+"";
                }
            };
            return dataTable;
        } catch (Exception pe) {
            return null;
        }
    }
    public String[][] vehicles(String login,int id) throws Exception{
        return ParseVehicles(GetVehicles(login,id),id);
    }

    public String PutVehicle(String login, int id, String[] data) throws Exception {
        URL url = new URL(IP() + "/vehicle/" + id);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("registration", data[1]);
            objp.put("model", data[2]);
            objp.put("horsepower", data[3]);
            objp.put("displacement", data[4]);
            objp.put("kilometers", data[5]);
            objp.put("fuel", data[6]);
            objp.put("fronttiresize", data[7]);
            objp.put("reartiresize", data[8]);
            objp.put("date", data[9]);
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
        JSONObject res = (JSONObject)new JSONParser().parse(json);
        return (String) res.get("result");
    }
    
    public String[] InfoVehicle(String login, int id) throws Exception{
        URL url = new URL(IP() + "/vehicle/"+id);
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
        String date =(String) newjsondata.get("date");
        String[] emp = new String[11];
        emp[0] = (long) newjsondata.get("idVehicle") + "";
        emp[1] = (String) newjsondata.get("nameBrand");
        emp[2] = (String) newjsondata.get("nameModel");
        emp[3] = (String) newjsondata.get("registration");
        emp[4] = (long) newjsondata.get("horsepower") + "";
        emp[5] = (long) newjsondata.get("displacement")+"";
        emp[6] = (long) newjsondata.get("kilometers") + "";
        emp[7] = (String) newjsondata.get("nameFuel");
        emp[8] = (String) newjsondata.get("fronttiresize");
        emp[9] = (String) newjsondata.get("reartiresize"); 
        emp[10]= date.substring(0, 10);
        return emp;
    }
}
