/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

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
public class APIRepair extends Connect {

    private String GetRepairs(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair" + ((id == 0) ? "" : "/" + id));
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
    public String[][] ParseListRepair(String list) throws Exception {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idRepair") + "";
                dataTable[i][1] = (String) datas.get("vehicle");
                dataTable[i][2] = (String) datas.get("service");
                dataTable[i][3] = (String) datas.get("description");
                dataTable[i][4] = (String) datas.get("price");
                dataTable[i][5] = (String) datas.get("state");
                dataTable[i][6] = (String) datas.get("startDate");
                dataTable[i][7] = (String) datas.get("finishDate");
                dataTable[i][8] = (String) datas.get("information");
                dataTable[i][9] = (String) datas.get("employer");
            };
            return dataTable;
        } catch (Exception pe) {
            return null;
        }
    }
    public String[][] Repairs(String login, int id) throws Exception {
        return ParseListRepair(GetRepairs(login, id));
    }
    public String PostRepair(String login, int vehicle,String description,int employer) throws Exception {
        URL url = new URL(IP() + "/repair");
        HttpURLConnection connection = Conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("vehicle", vehicle);
            objp.put("description", description);
            objp.put("employer", employer);
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
}
