/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.employers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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

    private String GetEmployerRepairs(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair/employer/" + id);
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
    private String[][] ParseListEmployerRepair(String list) throws Exception {
        try {
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
        } catch (Exception pe) {
            return null;
        }
    }

    /**
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[][] EployerRepairs(String login, int id) throws Exception {
        return ParseListEmployerRepair(GetEmployerRepairs(login, id));
    }

    public String PostEmployerRepair(String login, int idRepair, int empl) throws Exception{
        URL url = new URL(IP() + "/repair/employer");
        HttpURLConnection connection = Conn(login, url, "POST");
        JSONObject objp;
        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            objp = new JSONObject();
            objp.put("repair", idRepair);
            objp.put("employer", empl);
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
