/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import connect.Connect;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APIParts extends Connect{
    private String GetParts(String login, int id) throws Exception {
        URL url = new URL(IP() + "/parts" + ((id == 0) ? "" : "/service/" + id));
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
        }
        connection.disconnect();
        return json;
    }

    @SuppressWarnings("empty-statement")
    private String[][] ParseListParts(String list) throws Exception {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][6];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idPart") + "";
                dataTable[i][1] = (String) datas.get("namePart");
                dataTable[i][2] = (String) datas.get("description");
                dataTable[i][3] = (long) datas.get("amount")+"";
                dataTable[i][4] = (Object) datas.get("price")+"€";
                dataTable[i][5] = (((long) datas.get("isActive")==1)?"Produção":"Descontinuado");
            };
            return dataTable;
        } catch (Exception pe) {
            return null;
        }
    }
    public String[][] Parts(String login, int id) throws Exception {
        return ParseListParts(GetParts(login, id));
    }
}
