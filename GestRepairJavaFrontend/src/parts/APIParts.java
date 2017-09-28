/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import connect.Connect;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class APIParts extends Connect {

    /**
     * Lista todas as peças ou as peças por serviço
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("empty-statement")
    public String[][] ListParts(String login, int id) throws Exception {
        URL url = new URL(IP() + "/parts" + ((id == 0) ? "" : "/service/" + id));
        String result = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(result);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][6];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idPart") + "";
            dataTable[i][1] = (String) datas.get("namePart");
            dataTable[i][2] = (String) datas.get("description");
            dataTable[i][3] = (long) datas.get("amount") + "";
            dataTable[i][4] = (Object) datas.get("price") + "€";
            dataTable[i][5] = (((long) datas.get("isActive") == 1) ? "Produção" : "Descontinuado");
        };
        return dataTable;
    }

    /**
     * Lista o nome das peças por seviço
     *
     * @param login
     * @param idService
     * @return
     * @throws Exception
     */
    @SuppressWarnings("empty-statement")
    public String[][] ListPartsZero(String login, int idService) throws Exception {
        URL url = new URL(IP() + "/parts/service/zero/" + idService);
        String result = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(result);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idPart") + "";
            dataTable[i][1] = (String) datas.get("namePart");
        };
        return dataTable;
    }

    /**
     * Lista o os seviços que tem peças
     *
     * @param login
     * @return
     * @throws Exception
     */
    public String[][] ListPartswhith(String login) throws Exception {
        URL url = new URL(IP() + "/parts/service/whith/");
        String result = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(result);
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
     * Mostra os detalhes da peça
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] InfoParts(String login, int id) throws Exception {
        URL url = new URL(IP() + "/parts/" + id);
        String result = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(result);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] emp = new String[6];
        emp[0] = (long) newjsondata.get("idPart") + "";
        emp[1] = (String) newjsondata.get("namePart");
        emp[2] = (String) newjsondata.get("description");
        emp[3] = (long) newjsondata.get("amount") + "";
        emp[4] = (newjsondata.get("price") != null) ? (Object) newjsondata.get("price") + "" : null;
        emp[5] = (long) newjsondata.get("isActive") + "";
        return emp;
    }

    /**
     * Adiciona uma nova peça
     *
     * @param login
     * @param data
     * @return
     * @throws Exception
     */
    public String[] PostPart(String login, String[] data) throws Exception {
        URL url = new URL(IP() + "/parts");
        JSONObject objp = new JSONObject();
        objp.put("namePart", data[0]);
        objp.put("description", data[1]);
        objp.put("amount", data[2]);
        objp.put("price", data[3]);
        objp.put("service", data[4]);
        return SendConnect(login, url, "POST", objp);
    }

    /**
     * Edita uma peça
     *
     * @param login
     * @param data
     * @param idPart
     * @return
     * @throws Exception
     */
    public String[] PutPart(String login, String[] data, int idPart) throws Exception {
        URL url = new URL(IP() + "/parts/" + idPart);
        JSONObject objp = new JSONObject();
        objp.put("namePart", data[0]);
        objp.put("description", data[1]);
        return SendConnect(login, url, "PUT", objp);
    }

    /**
     * Adiciona Quantidade
     *
     * @param login
     * @param data
     * @param idPart
     * @return
     * @throws Exception
     */
    public String[] PUTAmount(String login, String data, int idPart) throws Exception {
        URL url = new URL(IP() + "/parts/amount/" + idPart);
        JSONObject objp = new JSONObject();
        objp.put("amount", data);
        return SendConnect(login, url, "PUT", objp);
    }

    /**
     * Altera o preço
     *
     * @param login
     * @param data
     * @param idPart
     * @return
     * @throws Exception
     */
    public String[] PUTPrice(String login, String data, int idPart) throws Exception {
        URL url = new URL(IP() + "/parts/price/" + idPart);
        JSONObject objp = new JSONObject();
        objp.put("price", data);
        return SendConnect(login, url, "PUT", objp);
    }
    /**
     * Adiciona serviço a uma determinada peça
     * @param login
     * @param data
     * @param idPart
     * @return
     * @throws Exception 
     */
    public String[] POSTService(String login, int data, int idPart) throws Exception {
        URL url = new URL(IP() + "/parts/service/" + idPart);
        JSONObject objp = new JSONObject();
        objp.put("service", data);
        return SendConnect(login, url, "POST", objp);
    }
    /**
     * Lista os Serviços associados a uma determinada peça
     * @param login
     * @param idParts
     * @return
     * @throws Exception 
     */
    public String[][] ListServiceParts(String login, int idParts) throws Exception {
        URL url = new URL(IP() + "/service/parts/" + idParts);
        String result = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(result);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idService") + "";
            dataTable[i][1] = (String) datas.get("nameService");
        }
        return dataTable;
    }
    /**
     * Lista Serviços que não estão associados a uma determinada peça
     * @param login
     * @param idParts
     * @return
     * @throws Exception 
     */
    public String[][] ListServiceNotParts(String login, int idParts) throws Exception {
        URL url = new URL(IP() + "/parts/service/not/" + idParts);
        String result = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(result);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idService") + "";
            dataTable[i][1] = (String) datas.get("nameService");
        }
        return dataTable;
    }
}
