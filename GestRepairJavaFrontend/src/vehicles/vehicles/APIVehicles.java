/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.vehicles;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Convite
 */
public class APIVehicles extends Connect {

    /**
     * VEHICLE
     */
    /**
     * POST Vehicle ADD Vehicle to database
     *
     * @param login
     * @param id
     * @param data
     * @return
     * @throws Exception
     */
    public String[] POSTAddVehicle(String login, int id, String[] data) throws Exception {
        URL url = new URL(IP() + "/vehicle/" + id + "/desk");
        JSONObject objp = new JSONObject();
        objp.put("model", data[0]);
        objp.put("registration", data[1]);
        objp.put("fuel", data[2]);
        objp.put("horsepower", data[3]);
        objp.put("displacement", data[4]);
        objp.put("kilometers", data[5]);
        objp.put("fronttiresize", data[6]);
        objp.put("reartiresize", data[7]);
        objp.put("date", data[8]);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     * Verifica se a viatura existe no sistema
     *
     * @param login
     * @param data
     * @return
     * @throws Exception
     */
    public String[] POSTVerifyVehicle(String login, String data) throws Exception {
        URL url = new URL(IP() + "/vehicle/exists");
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data);
        return SendConnectResp(login, url, "POST", objp);
    }

    /**
     * Verifica se a viatuta tem dono
     *
     * @param login
     * @param data
     * @return
     * @throws Exception
     */
    public String[] POSTVerifyOwnerVehicle(String login, String data) throws Exception {
        URL url = new URL(IP() + "/vehicle/exists/nuser");
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data);
        return SendConnectResp(login, url, "POST", objp);
    }

    /**
     * Verifica se a viatura já pertenceu à pessoa
     *
     * @param login
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    public String[] POSTVerifyUserVehicle(String login, String data, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/exists/user/" + id);
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data);
        return SendConnectResp(login, url, "POST", objp);
    }

    /**
     * Caso exista e a relação não existe associa ao utilizador
     *
     * @param login
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    public String[] POSTVerifyADDVehicle(String login, String data, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/exists/" + id);
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     * Termina a relação com uma pessoa
     *
     * @param login
     * @param data
     * @return
     * @throws Exception
     */
    public String[] POSTDisableVehicle(String login, String data) throws Exception {
        URL url = new URL(IP() + "/vehicle/disable/desktop");
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data);
        return SendConnect(login, url, "PUT", objp);
    }

    /**
     * Caso a relação exista adiciona altera reactiva a ligação
     *
     * @param login
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    public String[] PUTVerifyADDVehicle(String login, String data, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/exists/" + id);
        JSONObject objp = new JSONObject();
        objp.put("vehicle", data);
        return SendConnect(login, url, "PUT", objp);
    }

    /**
     * Mostra a lista de viaturas
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[][] vehicles(String login, int id) throws Exception {
        URL url;
        if (id == 0) {
            url = new URL(IP() + "/vehicle/");
        } else {
            url = new URL(IP() + "/vehicle/" + id + "/user");
        }
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][(id == 0) ? 12 : 11];
        for (int i = 0; i < data.size(); i++) {

            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idVehicle") + "";
            dataTable[i][1] = (String) datas.get("registration");
            dataTable[i][2] = (String) datas.get("nameBrand");
            dataTable[i][3] = (String) datas.get("nameModel");
            dataTable[i][4] = (datas.get("horsepower") != null) ? ((Object) datas.get("horsepower")) + "" : "";
            dataTable[i][5] = (datas.get("displacement") != null) ? ((Object) datas.get("displacement")) + "" : "";
            dataTable[i][6] = (datas.get("kilometers") != null) ? ((Object) datas.get("kilometers")) + "" : "";
            dataTable[i][7] = (String) datas.get("nameFuel");
            dataTable[i][8] = (String) datas.get("fronttiresize");
            dataTable[i][9] = (String) datas.get("reartiresize");
            dataTable[i][10] = (datas.get("date") != null) ? ((String) datas.get("date")).substring(0, 10) : "";
            if (id == 0) {
                dataTable[i][11] = (String) datas.get("username") + "";
            }
        };
        return dataTable;
    }

    /**
     * Edita os dados da viatura
     *
     * @param login
     * @param id
     * @param data
     * @return
     * @throws Exception
     */
    public String[] PutVehicle(String login, int id, String[] data) throws Exception {
        URL url = new URL(IP() + "/vehicle/" + id);
        JSONObject objp = new JSONObject();
        objp.put("registration", data[1]);
        objp.put("model", data[2]);
        objp.put("horsepower", data[3]);
        objp.put("displacement", data[4]);
        objp.put("kilometers", data[5]);
        objp.put("fuel", data[6]);
        objp.put("fronttiresize", data[7]);
        objp.put("reartiresize", data[8]);
        objp.put("date", data[9]);
        return SendConnect(login, url, "PUT", objp);
    }

    /**
     * Mostra os detalhes da viatura
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] InfoVehicle(String login, int id) throws Exception {
        URL url = new URL(IP() + "/vehicle/" + id);
        String json = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String date = (String) newjsondata.get("date");
        String[] emp = new String[11];
        emp[0] = (long) newjsondata.get("idVehicle") + "";
        emp[1] = (String) newjsondata.get("nameBrand");
        emp[2] = (String) newjsondata.get("nameModel");
        emp[3] = (String) newjsondata.get("registration");
        emp[4] = (newjsondata.get("horsepower") != null) ? ((Object) newjsondata.get("horsepower")) + "" : "";
        emp[5] = (newjsondata.get("displacement") != null) ? ((Object) newjsondata.get("displacement")) + "" : "";
        emp[6] = (newjsondata.get("kilometers") != null) ? ((Object) newjsondata.get("kilometers")) + "" : "";
        emp[7] = (String) newjsondata.get("nameFuel");
        emp[8] = (String) newjsondata.get("fronttiresize");
        emp[9] = (String) newjsondata.get("reartiresize");
        emp[10] = date.substring(0, 10);
        return emp;
    }
}
