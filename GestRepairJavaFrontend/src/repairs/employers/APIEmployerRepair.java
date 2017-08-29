/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.employers;

import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import repairs.repairs.APIRepair;

/**
 *
 * @author Rui Barcelos
 */
public class APIEmployerRepair extends APIRepair {

    @SuppressWarnings("empty-statement")
    public String[][] EployerRepairs(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair/employer/" + id);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idEmployer") + "";
            dataTable[i][1] = (String) datas.get("name");
            dataTable[i][2] = (String) datas.get("nameService");
        };
        return dataTable;
    }

    public String[] PostEmployerRepair(String login, int idRepair, int empl) throws Exception {
        URL url = new URL(IP() + "/repair/employer");
        JSONObject objp = new JSONObject();
        objp.put("repair", idRepair);
        objp.put("employer", empl);
        return SendConnect(login, url, "POST", objp);
    }
}
