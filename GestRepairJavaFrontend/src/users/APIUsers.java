/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import ip.Connect;
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
public class APIUsers {

    Connect connect = new Connect();
    HttpURLConnection connection;

    public void conn(String login, URL url, String method) throws Exception {
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
    /*
     *Users
     */

    public String PostUser(String login, String name, String street, String zipCode, String city, String email, String nif, String contact, String username) throws Exception {
        URL url = new URL(connect.IP() + "/user/desk");
        conn(login, url, "POST");

        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("name", name);
            objp.put("street", street);
            objp.put("zipcode", zipCode);
            objp.put("city", city);
            objp.put("email", email);
            objp.put("nif", nif);
            objp.put("contact", contact);
            objp.put("username", username);
            objp.put("password", nif);
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
        URL url = new URL(connect.IP() + ((id == 2) ? "/user" : "/user/type/" + id));
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
    public String ActivityEmplyer(String login, int id, int opt) throws Exception {
        URL url = new URL(connect.IP() + "/user/employer/" + id+"/activity/"+opt);
        conn(login, url, "PUT");
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
    public String PutPassword(String login, int id, String oldpassword, String newpassword) throws Exception {
        URL url = new URL(connect.IP() + "/chpass/" + id);
        conn(login, url, "PUT");
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
    
    public void PutUser(String login, String numUser, String name, String street, String zipCode, String city, String email, String nif, String contact) throws Exception {
        URL url = new URL(connect.IP() + "/user/" + numUser);
        conn(login, url, "PUT");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("name", name);
            objp.put("street", street);
            objp.put("zipcode", zipCode);
            objp.put("city", city);
            objp.put("email", email);
            objp.put("nif", nif);
            objp.put("contact", contact);
            out.write(objp.toString());
            out.flush();
        }
        connection.getResponseCode();

        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String json = null;
        while ((line = br.readLine()) != null) {
            json += line;
        }
        connection.disconnect();
        /*JSONObject res = (JSONObject) new JSONParser().parse(json);
        return (String) res.get("result");*/
    }

    public String[] GetInfoUser(String login, int id) throws Exception {
        URL url = new URL(connect.IP() + "/user/" + id);
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
    /*
     *Employers
     */

    public String PostEmployer(String login, int idUser, int service) throws Exception {
        URL url = new URL(connect.IP() + "/user/employer");
        conn(login, url, "POST");
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

    public String GetListEmployer(String login, int id, int serv) throws Exception {
        URL url = new URL(connect.IP() + "/user/employer/type/" + id +((serv==0)?"":"/"+serv));

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

    @SuppressWarnings("empty-statement")
    public String[][] ParseEmployer(String list, int id, int serv) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");

            String[][] dataTable = new String[data.size()][(serv==0)?3:2];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idEmployer") + "";
                dataTable[i][1] = (String) datas.get("name");
                if (serv == 0){
                    dataTable[i][2] = (String) datas.get("service");
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
        URL url = new URL(connect.IP() + "/user/employer/" + id);
        conn(login, url, "GET");

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
        String[] emp = new String[4];
        emp[0] = (long) newjsondata.get("idEmployer") + "";
        emp[1] = (String) newjsondata.get("name");
        emp[2] = (String) newjsondata.get("nameService");
        emp[3] = (long) newjsondata.get("isActive") + "";
        return emp;
    }
}
