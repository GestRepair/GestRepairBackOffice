/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.employer;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APIEmployer extends Connect {

    public String ActivityEmplyer(String login, int id, int opt) throws Exception {
        URL url = new URL(IP() + "/user/employer/" + id + "/activity/" + opt);
        JSONObject objp = new JSONObject();
        return SendConnect(login, url, "PUT", objp);
    }

    public String PostEmployer(String login, int idUser, int service) throws Exception {
        URL url = new URL(IP() + "/user/employer");
        JSONObject objp = new JSONObject();
        objp.put("user", idUser);
        objp.put("service", service);
        return SendConnect(login, url, "POST", objp);
    }
    /*
     *Employers
     */
    public String PutEmployer(String login, int id, int service) throws Exception {
        URL url = new URL(IP() + "/user/employer/" + id);
        JSONObject objp = new JSONObject();
        objp.put("service", service);
        return SendConnect(login, url, "PUT", objp);
    }

    @SuppressWarnings("empty-statement")
    public String[][] ShowEmployer(String login, int id, int serv) throws Exception {
        URL url = new URL(IP() + "/user/employer/type/" + id + ((serv == 0) ? "" : "/" + serv));
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");

        String[][] dataTable = new String[data.size()][(serv == 0) ? 4 : 3];
        for (int i = 0; i < data.size(); i++) {

            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idEmployer") + "";
            dataTable[i][1] = (long) datas.get("user") + "";
            dataTable[i][2] = (String) datas.get("name");
            if (serv == 0) {
                dataTable[i][3] = (String) datas.get("service");
            }

        };
        return dataTable;
    }
    public String[][] ShowNotRepairEmployer(String login, int id, int serv) throws Exception {
        URL url = new URL(IP() + "/repair/employer/" + id +"/" + serv);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idEmployer") + "";
            dataTable[i][1] = (String) datas.get("name");
        };
        return dataTable;
    }
    
    public String[] GetInfoEmployerUser(String login, int id) throws Exception {
        URL url = new URL(IP() + "/user/employer/" + id);
        return GETInfoEmployer( login, url, id);
    }
    public String[] InfoEmployer(String login, int id) throws Exception {
        URL url = new URL(IP() + "/employer/" + id);
        return GETInfoEmployer( login, url, id);
    }
    public String[] GETInfoEmployer(String login,URL url, int id) throws Exception {
        String json = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[5];
        emp[0] = (long) newjsondata.get("idEmployer") + "";
        emp[1] = (String) newjsondata.get("name");
        emp[2] = (String) newjsondata.get("nameService");
        emp[3] = (long) newjsondata.get("service") + "";
        emp[4] = (long) newjsondata.get("isActive") + "";
        return emp;
    }
}
