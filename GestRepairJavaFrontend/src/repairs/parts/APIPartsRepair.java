/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.parts;

import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import repairs.repairs.APIRepair;

/**
 *
 * @author Rui Barcelos
 */
public class APIPartsRepair extends APIRepair {

    @SuppressWarnings("empty-statement")
    public String[][] ListParts(String login, int idRepair) throws Exception {
        URL url = new URL(IP() + "/repair/parts/" + idRepair);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idPart")+"";
            dataTable[i][1] = (String) datas.get("namePart");
        };
        return dataTable;
    }
    public String PostParts(String login, int idRepair, int idPart) throws Exception {
        URL url = new URL(IP() + "/repair/parts/"+idRepair);
        JSONObject objp = new JSONObject();
        objp.put("part", idPart);
        return SendConnect(login, url, "POST", objp);
    }
}
