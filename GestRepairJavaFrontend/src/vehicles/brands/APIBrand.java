/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.brands;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APIBrand extends Connect {

    /**
     * BRAND
     */
    /**
     * POST BRAND ADD to database new Brand
     *
     * @param login
     * @param brand
     * @return
     * @throws Exception
     */
    public String PostBrand(String login, String brand) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand");
        JSONObject objp = new JSONObject();
        objp.put("brand", brand);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     * Convert list to Array Two Dimensional
     *
     * @param login
     * @return
     * @throws java.lang.Exception
     */
    public String[][] Brand(String login) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][10];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idBrand") + "";
            dataTable[i][1] = (String) datas.get("nameBrand");
        };
        return dataTable;
    }
    /**
     * GET Info data on user
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] InfoBrand(String login, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand/" + id);
        String json = GETConnect(login, url, "GET");
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
     *
     * @param login
     * @param id
     * @param brand
     * @return
     * @throws Exception
     */
    public String PutBrand(String login, int id, String brand) throws Exception {
        URL url = new URL(IP() + "/vehicle/brand/" + id);
        JSONObject objp = new JSONObject();
        objp.put("nameBrand", brand);
        return SendConnect(login, url, "PUT", objp);
    }
}
