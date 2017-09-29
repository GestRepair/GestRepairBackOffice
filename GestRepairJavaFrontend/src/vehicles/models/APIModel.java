/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.models;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
     * @return
     * @throws Exception
     */
    public String[] PostModel(String login, int brand, String model) throws Exception {
        URL url = new URL(IP() + "/vehicle/model");
        JSONObject objp = new JSONObject();
        objp.put("brand", brand);
        objp.put("model", model);
        return SendConnect(login, url, "POST", objp);
    }
    /**
     * Mostra a listagem dos dados nados modelos
     * @param login
     * @param id
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("empty-statement")
    public String[][] Model(String login, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/" + id + "/model/");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][10];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idModel") + "";
            dataTable[i][1] = (String) datas.get("nameModel");
        };
        return dataTable;
    }

    /**
     * Put the data inside the array
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] InfoModel(String login, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/model/" + id);
        String json = GETConnect(login, url, "GET");
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
     *
     * @param login
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    public String[] PutModel(String login, int id, String model) throws Exception {
        URL url = new URL(IP() + "/vehicle/model/" + id);
        JSONObject objp = new JSONObject();
        objp.put("nameModel", model);
        return SendConnect(login, url, "PUT", objp);
    }
}
