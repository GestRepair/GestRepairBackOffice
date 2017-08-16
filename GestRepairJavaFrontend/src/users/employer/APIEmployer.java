/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.employer;

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
public class APIEmployer extends Connect {
    public String ActivityEmplyer(String login, int id, int opt) throws Exception {
        URL url = new URL(IP() + "/user/employer/" + id+"/activity/"+opt);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            out.write(objp.toString());
            out.flush();
        }
        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        JSONObject res = (JSONObject) new JSONParser().parse(json);
        return (String) res.get("result");
    }
    public String PostEmployer(String login, int idUser, int service) throws Exception {
        URL url = new URL(IP() + "/user/employer");
        HttpURLConnection connection = Conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("user", idUser);
            objp.put("service", service);
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
    /*
     *Employers
     */

    
    public String PutEmployer(String login, int id, int service) throws Exception {
        URL url = new URL(IP() + "/user/employer/"+id);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("service", service);
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

    public String GetListEmployer(String login, int id, int serv) throws Exception {
        URL url = new URL(IP() + "/user/employer/type/" + id +((serv==0)?"":"/"+serv));

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

    @SuppressWarnings("empty-statement")
    public String[][] ParseEmployer(String list, int id, int serv) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");

            String[][] dataTable = new String[data.size()][(serv==0)?4:3];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idEmployer") + "";
                dataTable[i][1] = (long) datas.get("user")+"";
                dataTable[i][2] = (String) datas.get("name");
                if (serv == 0){
                    dataTable[i][3] = (String) datas.get("service");
                }
                
            };
            return dataTable;
        } catch (ParseException pe) {
            System.err.println(pe);
            return null;
        }
    }

    public String[][] ShowEmployer(String login, int id, int serv) throws Exception {
        return ParseEmployer(GetListEmployer(login, id, serv), id, serv);
    }

    public String[] GetInfoEmployer(String login, int id) throws Exception {
        URL url = new URL(IP() + "/user/employer/" + id);
        HttpURLConnection connection = Conn(login, url, "GET");

        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String json = "";
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[5];
        emp[0] = (long) newjsondata.get("idEmployer") + "";
        emp[1] = (String) newjsondata.get("name");
        emp[2] = (String) newjsondata.get("nameService");
        emp[3] = (long) newjsondata.get("service")+"";
        emp[4] = (long) newjsondata.get("isActive") + "";
        return emp;
    }
}
