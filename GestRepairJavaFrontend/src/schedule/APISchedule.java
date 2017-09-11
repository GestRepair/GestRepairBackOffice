/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APISchedule extends Connect {

    @SuppressWarnings("empty-statement")
    public String[][] ListSchedule(String login, int id) throws Exception {
        URL url = new URL(IP() + "/schedule/" + ((id == 0) ? "" : "/" + id));
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][4];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idSchedule") + "";
            dataTable[i][1] = (String) datas.get("vehicle");
            dataTable[i][2] = (String) datas.get("service");
            dataTable[i][3] = (String) datas.get("date");
        };
        return dataTable;
    }

    @SuppressWarnings("empty-statement")
    public String[][] ListDays(String login) throws Exception {
        URL url = new URL(IP() + "/schedule/days");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idDate") + "";
            dataTable[i][1] = (String) datas.get("nameDate");
        };
        return dataTable;
    }

    public String[][] ListTime(String login, int id) throws Exception {
        String moment;
        switch (id) {
            case 1:
                moment = "now";
                break;
            case 2:
                moment = "previous";
                break;
            case 3:
                moment = "next";
                break;
            default:
                moment = "next";
                break;
        }
        URL url = new URL(IP() + "/schedule/" + moment);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][4];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idSchedule") + "";
            dataTable[i][1] = (Object) datas.get("vehicle") + "";
            dataTable[i][2] = (Object) datas.get("service") + "";
            dataTable[i][3] = ((String) datas.get("date")).substring(0,10)+" - "+((String) datas.get("date")).substring(11,16);
        };
        return dataTable;
    }

    public String[][] ListTimeUser(String login, int id, int idUser) throws Exception {
        String moment;
        switch (id) {
            case 1:
                moment = "now";
                break;
            case 2:
                moment = "previous";
                break;
            case 3:
                moment = "next";
                break;
            default:
                moment = "next";
                break;
        }
        URL url = new URL(IP() + "/schedule/" + moment +"/"+ idUser);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][4];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idSchedule") + "";
            dataTable[i][1] = (Object) datas.get("vehicle") + "";
            dataTable[i][2] = (Object) datas.get("service") + "";
            dataTable[i][3] = ((String) datas.get("date")).substring(0,10)+" - "+((String) datas.get("date")).substring(11,16);
        };
        return dataTable;
    }
}
