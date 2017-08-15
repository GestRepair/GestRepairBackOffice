/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

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

/**
 *
 * @author Rui Barcelos
 */
public class APIUsers extends Connect {
    /*
     *Users
     */
    public String PostUser(String login, String[] data) throws Exception {
        URL url = new URL(IP() + "/user/desk");
        HttpURLConnection connection = Conn(login, url, "POST");

        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("name", data[0]);
            objp.put("street", data[1]);
            objp.put("zipcode", data[2]);
            objp.put("city", data[3]);
            objp.put("email", data[4]);
            objp.put("nif", data[5]);
            objp.put("contact", data[6]);
            objp.put("username", data[7]);
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

    public String GetUserList(String login, int id) throws Exception {
        URL url = new URL(IP() + ((id == 2) ? "/user" : "/user/type/" + id));
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
    public String[][] ParseUsers(String list, int id) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][(id == 2) ? 10 : 9];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idUser") + "";
                dataTable[i][1] = (String) datas.get("name");
                dataTable[i][2] = (String) datas.get("street");
                dataTable[i][3] = (String) datas.get("zipcode");
                dataTable[i][4] = (String) datas.get("city");
                dataTable[i][5] = (String) datas.get("email");
                dataTable[i][6] = (String) datas.get("nif");
                dataTable[i][7] = (String) datas.get("contact");
                dataTable[i][8] = (String) datas.get("username");
                if (id == 2) {
                    dataTable[i][9] = ((long) datas.get("isEmployer") == 1) ? "Funcionário" : "Cliente";
                }
            }
            return dataTable;
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

    public String[][] ShowUser(String login, int id) throws Exception {
        return ParseUsers(GetUserList(login, id), id);
    }
    
    public String PutPassword(String login, int id, String oldpassword, String newpassword) throws Exception {
        URL url = new URL(IP() + "/chpass/" + id);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("oldPassword", oldpassword);
            objp.put("newPassword", newpassword);
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
    
    public String PutUser(String login, String numUser, String[] data) throws Exception {
        URL url = new URL(IP() + "/user/" + numUser);
        HttpURLConnection connection = Conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("name", data[0]);
            objp.put("street", data[1]);
            objp.put("zipcode", data[2]);
            objp.put("city", data[3]);
            objp.put("email", data[4]);
            objp.put("nif", data[5]);
            objp.put("contact", data[6]);
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

    public String[] GetInfoUser(String login, int id) throws Exception {
        URL url = new URL(IP() + "/user/" + id);
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
        String[] emp = new String[11];
        emp[0] = (long) newjsondata.get("idUser") + "";
        emp[1] = (String) newjsondata.get("name");
        emp[2] = (String) newjsondata.get("street");
        emp[3] = (String) newjsondata.get("zipcode");
        emp[4] = (String) newjsondata.get("city");
        emp[5] = (String) newjsondata.get("email");
        emp[6] = (String) newjsondata.get("contact");
        emp[7] = (String) newjsondata.get("nif");
        emp[8] = (String) newjsondata.get("username");
        emp[9] = (long) newjsondata.get("isActive") + "";
        emp[10] = (long) newjsondata.get("isEmployer") + "";
        return emp;
    }
    
}
