/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APIRepair extends Connect {

    @SuppressWarnings("empty-statement")
    public String[][] ListRepairs(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair" + ((id == 0) ? "" : "/user/" + id));
        return GETListRepair(login, url);
    }

    public String[][] ListRepairsState(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair/states/" + id);
        return GETListRepair(login, url);
    }
    

    private String[][] GETListRepair(String login, URL url) throws Exception {
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][8];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idRepair") + "";
            dataTable[i][1] = (String) datas.get("vehicle");
            dataTable[i][2] = (String) datas.get("description");
            dataTable[i][3] = (datas.get("price") != null) ? (Object) datas.get("price") + "" : "";
            dataTable[i][4] = ((String) datas.get("startDate")).substring(0, 10) + " - " + ((String) datas.get("startDate")).substring(11, 16);
            dataTable[i][5] = (datas.get("finishDate") != null) ? ((String) datas.get("finishDate")).substring(0, 10) + " - " + ((String) datas.get("finishDate")).substring(11, 16): null;
            dataTable[i][6] = (String) datas.get("information");
            dataTable[i][7] = (String) datas.get("state");
        };
        return dataTable;
    }

    public String[] PostRepair(String login, int vehicle, String description, int employer) throws Exception {
        URL url = new URL(IP() + "/repair");
        JSONObject objp = new JSONObject();
        objp.put("vehicle", vehicle);
        objp.put("description", description);
        objp.put("employer", employer);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] GetInfoRepair(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair/" + id);
        String json = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[8];
        emp[0] = (long) newjsondata.get("idRepair") + "";
        emp[1] = (String) newjsondata.get("registration");
        emp[2] = (String) newjsondata.get("description");
        emp[3] = (newjsondata.get("price") != null) ? (Object) newjsondata.get("price") + "" : null;
        emp[4] = (String) newjsondata.get("nameState");
        emp[5] = ((String) newjsondata.get("startDate")).substring(0, 10);
        emp[6] = (newjsondata.get("finishDate") != null) ? ((String) newjsondata.get("finishDate")).substring(0, 10) : null;
        emp[7] = (String) newjsondata.get("information");
        return emp;
    }

    public String[] UpdateRepair(String login, int id, String[] data) throws Exception {
        URL url = new URL(IP() + "/repair/" + id);
        JSONObject objp = new JSONObject();
        objp.put("description", data[0]);
        objp.put("price", data[1]);
        objp.put("state", data[2]);
        objp.put("information", data[3]);
        return SendConnect(login, url, "PUT", objp);
    }
}
