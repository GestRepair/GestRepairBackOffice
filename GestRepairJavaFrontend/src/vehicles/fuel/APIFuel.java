/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.fuel;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Convite
 */
public class APIFuel extends Connect {

    public String[][] Fuel(String login) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][10];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idFuel") + "";
            dataTable[i][1] = (String) datas.get("nameFuel");
        };
        return dataTable;
    }

    public String[] InfoFuel(String login, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel/" + id);
        String json = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[2];
        emp[0] = (long) newjsondata.get("idFuel") + "";
        emp[1] = (String) newjsondata.get("nameFuel");
        return emp;
    }

    public String[] PutFuel(String login, int id, String fuel) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel/" + id);
        JSONObject objp = new JSONObject();
        objp.put("nameFuel", fuel);
        return SendConnect(login, url, "PUT", objp);
    }
    public String[] PostFuel(String login, String fuel) throws Exception {
        URL url = new URL(IP() + "/vehicle/fuel");
        JSONObject objp = new JSONObject();
        objp.put("nameFuel", fuel);
        return SendConnect(login, url, "POST", objp);
    }
}
