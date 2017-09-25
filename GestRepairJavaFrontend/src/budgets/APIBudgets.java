/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgets;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APIBudgets extends Connect {

    /**
     * Lista orçamentos
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("empty-statement")
    public String[][] ListBudgets(String login, int id) throws Exception {
        URL url = new URL(IP() + "/budget" + ((id == 0) ? "" : "/" + id));
        return dataBudget(login, url);
    }

    /**
     * Lista os Estados do orçamento
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[][] ListBudgetsByState(String login, int id) throws Exception {
        URL url = new URL(IP() + "/budget/state/" + id);
        return dataBudget(login, url);
    }

    /**
     * Lista de forma genérica os dados
     *
     * @param login
     * @param url
     * @return
     * @throws Exception
     */
    private String[][] dataBudget(String login, URL url) throws Exception {
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][9];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idBudget") + "";
            dataTable[i][1] = (String) datas.get("vehicle");
            dataTable[i][2] = (String) datas.get("description");
            dataTable[i][3] = ((datas.get("price") != null) ? (Object) datas.get("price") + "" : "");
            dataTable[i][4] = ((String) datas.get("processOpen")).substring(0, 10);
            dataTable[i][5] = ((datas.get("repairTime") != null) ? (Object) datas.get("repairTime") + "" : "");
            dataTable[i][6] = ((datas.get("processClose") != null) ? ((String) datas.get("processClose")).substring(0, 10) + "" : "");
            dataTable[i][7] = ((datas.get("price") != null) ? (String) datas.get("resolution") + "" : "");
            dataTable[i][8] = (String) datas.get("state");
        };
        return dataTable;
    }

    /**
     * Lista o estados do orçamento
     *
     * @param login
     * @return
     * @throws Exception
     */
    public String[][] ListState(String login) throws Exception {
        URL url = new URL(IP() + "/budget/state");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][9];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idState") + "";
            dataTable[i][1] = (String) datas.get("nameState");
        };
        return dataTable;
    }

    /**
     * Lista completa de estados do orçamento
     *
     * @param login
     * @return
     * @throws Exception
     */
    public String[][] ListStateComplete(String login) throws Exception {
        URL url = new URL(IP() + "/budget/state/complete");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][9];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idState") + "";
            dataTable[i][1] = (String) datas.get("nameState");
        };
        return dataTable;
    }

    /**
     * Lista os serviços que estão associados a um orçamento
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[][] ListService(String login, int id) throws Exception {
        URL url = new URL(IP() + "/budget/service/" + id);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("service") + "";
            dataTable[i][1] = (String) datas.get("nameService");
        };
        return dataTable;
    }

    /**
     * Lista de serviços que não está utilizado no orçamento
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[][] ListServiceNot(String login, int id) throws Exception {
        URL url = new URL(IP() + "/budget/service/not/" + id);
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idService") + "";
            dataTable[i][1] = (String) datas.get("nameService");
        };
        return dataTable;
    }

    /**
     * Adiciona um orçamento
     *
     * @param login
     * @param data
     * @return
     * @throws Exception
     */
    public String[] POSTAddBudget(String login, String[] data) throws Exception {
        URL url = new URL(IP() + "/budget");
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data[0]);
        objp.put("description", data[1]);
        objp.put("service", data[2]);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     * Adiciona peças ao orçamento
     *
     * @param login
     * @param data
     * @return
     * @throws Exception
     */
    public String[] POSTAddPart(String login, String[] data) throws Exception {
        URL url = new URL(IP() + "/budget/part");
        JSONObject objp = new JSONObject();
        objp.put("budget", data[0]);
        objp.put("service", data[1]);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     * Mostra a informação de um determinado orçamento
     * 
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] GetInfoBudget(String login, int id) throws Exception {
        URL url = new URL(IP() + "/budget/info/" + id);
        String json = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[9];
        emp[0] = (long) newjsondata.get("idBudget") + "";
        emp[1] = (String) newjsondata.get("vehicle");
        emp[2] = (String) newjsondata.get("description");
        emp[3] = (String) newjsondata.get("state");
        emp[4] = (newjsondata.get("price") != null) ? (Object) newjsondata.get("price") + "" : null;
        emp[5] = ((String) newjsondata.get("processOpen")).substring(0, 10) + " - " + ((String) newjsondata.get("processOpen")).substring(11, 16);
        emp[6] = (newjsondata.get("repairTime") != null) ? ((long) newjsondata.get("repairTime")) + "" : "";
        emp[7] = (newjsondata.get("processClose") != null) ? ((String) newjsondata.get("processClose")).substring(0, 10) + " - " + ((String) newjsondata.get("processClose")).substring(11, 16) : "";
        emp[8] = (String) newjsondata.get("resolution");
        return emp;
    }
    /**
     * Atualiza os dados do orçamento
     * @param login
     * @param id
     * @param data
     * @return
     * @throws Exception 
     */
    public String[] UpdateBudget(String login, int id, String[] data) throws Exception {
        URL url = new URL(IP() + "/budget/" + id);
        JSONObject objp = new JSONObject();
        objp.put("description", data[0]);
        objp.put("price", data[1]);
        objp.put("state", data[2]);
        objp.put("repairTime", data[3]);
        objp.put("resolution", data[4]);
        return SendConnect(login, url, "PUT", objp);
    }
}
