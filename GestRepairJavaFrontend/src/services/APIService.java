/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connect.Connect;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.currentTimeMillis;
import java.net.HttpURLConnection;
import static java.net.HttpURLConnection.HTTP_OK;
import java.net.MalformedURLException;
import java.net.URL;
import static java.net.URLConnection.guessContentTypeFromName;
import static java.text.MessageFormat.format;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public class APIService extends Connect {

    private static final String LINE_FEED = "\r\n";
    private static final String CHARSET = "UTF-8";
    private long start;

    /**
     * URL de postagem
     *
     * @return @throws MalformedURLException
     */
    public URL POSTUrl() throws MalformedURLException {
        URL url = new URL(IP() + "/service");
        return url;
    }

    /**
     * URL para o put do serviço com foto
     *
     * @param id
     * @return
     * @throws MalformedURLException
     */
    public URL PUTUrl(int id) throws MalformedURLException {
        URL url = new URL(IP() + "/service/" + id);
        return url;
    }

    /**
     * URL de atualizar serviço sem foto
     *
     * @param id
     * @return
     * @throws MalformedURLException
     */
    public URL PUTUrlWithout(int id) throws MalformedURLException {
        URL url = new URL(IP() + "/service/" + id + "/without");
        return url;
    }
    OutputStream outputStream;
    PrintWriter writer;
    String boundary;
    HttpURLConnection connection;

    /**
     * Criar um novo serviço
     *
     * @param login
     * @param name
     * @param price
     * @param description
     * @param photo
     * @throws Exception
     */
    public void PostService(String login, String name, String price, String description, File photo) throws Exception {
        final File uploadFile = photo;
        try {
            Connection(login, "POST", POSTUrl());
            addFormField("nameService", name);
            addFormField("priceService", price);
            addFormField("description", description);
            addFilePart("photo", uploadFile);
            final byte[] bytes = finish(POSTUrl());
            //final OutputStream os = new FileOutputStream("someoutput.txt");
            //os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Conectar para a atualização do serviço
     *
     * @param login
     * @param method
     * @param url
     * @throws UnsupportedEncodingException
     * @throws ParseException
     * @throws IOException
     */
    public void Connection(String login, String method, URL url) throws UnsupportedEncodingException, ParseException, IOException {

        start = currentTimeMillis();

        boundary = "---------------------------" + currentTimeMillis();

        JSONObject newjson = (JSONObject) new JSONParser().parse(login);
        String user = newjson.get("login").toString();
        String pass = newjson.get("password").toString();
        byte[] message = (user + ":" + pass).getBytes(CHARSET);
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(15000);//15 secs
        connection.setReadTimeout(10000);//10 secs
        connection.setRequestMethod(method);
        connection.setRequestProperty("Accept", CHARSET);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        outputStream = connection.getOutputStream();

        writer = new PrintWriter(new OutputStreamWriter(outputStream, CHARSET), true);
    }

    /**
     * Serve para enviar dados
     *
     * @param name
     * @param value
     */
    public void addFormField(final String name, final String value) {
        writer.append("--").append(boundary).append(LINE_FEED)
                .append("Content-Disposition: form-data; name=\"").append(name)
                .append("\"").append(LINE_FEED)
                .append("Content-Type: text/plain; charset=").append(CHARSET)
                .append(LINE_FEED).append(LINE_FEED).append(value).append(LINE_FEED);
    }

    /**
     * Adicionar ficheiros por binário
     *
     * @param fieldName
     * @param uploadFile
     * @throws IOException
     */
    public void addFilePart(final String fieldName, final File uploadFile)
            throws IOException {
        final String fileName = uploadFile.getName();
        writer.append("--").append(boundary).append(LINE_FEED)
                .append("Content-Disposition: form-data; name=\"")
                .append(fieldName).append("\"; filename=\"").append(fileName)
                .append("\"").append(LINE_FEED).append("Content-Type: ")
                .append(guessContentTypeFromName(fileName)).append(LINE_FEED)
                .append("Content-Transfer-Encoding: binary").append(LINE_FEED)
                .append(LINE_FEED);

        writer.flush();
        outputStream.flush();
        try (final FileInputStream inputStream = new FileInputStream(uploadFile);) {
            final byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        }

        writer.append(LINE_FEED);
    }

    /**
     * Adicionar header
     *
     * @param name
     * @param value
     */
    public void addHeaderField(String name, String value) {
        writer.append(name).append(": ").append(value).append(LINE_FEED);
    }

    /**
     * Fim de ligação
     *
     * @param url
     * @return
     * @throws IOException
     */
    public byte[] finish(URL url) throws IOException {
        writer.append(LINE_FEED).append("--").append(boundary).append("--")
                .append(LINE_FEED);
        writer.close();

        final int status = connection.getResponseCode();
        if (status != HTTP_OK) {
            throw new IOException(format("{0} failed with HTTP status: {1}",
                    url, status));
        }

        try (final InputStream is = connection.getInputStream()) {
            final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            final byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                bytes.write(buffer, 0, bytesRead);
            }
            return bytes.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    /**
     * Vai buscar os dados para postriormente serem inseridos na tabela
     *
     * @param login
     * @return
     * @throws Exception
     */
    public String[][] Service(String login) throws Exception {
        URL url = new URL(IP() + "/service/comp");
        String list = GETConnect(login, url, "GET");
        JSONObject jo = (JSONObject) new JSONParser().parse(list);
        JSONArray data = (JSONArray) jo.get("data");
        String[][] dataTable = new String[data.size()][5];
        for (int i = 0; i < data.size(); i++) {
            JSONObject datas = (JSONObject) data.get(i);
            dataTable[i][0] = (long) datas.get("idService") + "";
            dataTable[i][1] = (String) datas.get("nameService");
            dataTable[i][2] = (long) datas.get("priceService") + "";
            dataTable[i][3] = (String) datas.get("description");
            dataTable[i][4] = (String) datas.get("photo");
        };
        return dataTable;

    }

    /**
     * Mostra a informação do serviço
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[] GetInfo(String login, int id) throws Exception {
        URL url = new URL(IP() + "/service/desk/" + id);
        String json = GETConnect(login, url, "GET");
        JSONObject newjson = (JSONObject) new JSONParser().parse(json);
        String data = newjson.get("data").toString();
        JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
        String[] info = new String[5];
        info[0] = (long) newjsondata.get("idService") + "";
        info[1] = (String) newjsondata.get("nameService");
        info[2] = (newjsondata.get("priceService") != null) ? (Object) newjsondata.get("priceService") + "" : null;
        info[3] = (String) newjsondata.get("description");
        info[4] = IP() + "/service/img/" + (long) newjsondata.get("idService");
        return info;
    }

    /**
     * Put Service with photo
     *
     * @param login
     * @param id
     * @param name
     * @param price
     * @param description
     * @param photo
     * @throws Exception
     */
    public void PutService(String login, int id, String name, String price, String description, File photo) throws Exception {
        final File uploadFile = photo;
        Connection(login, "PUT", PUTUrl(id));
        addFormField("nameService", name);
        addFormField("priceService", price);
        addFormField("description", description);
        addFilePart("photo", uploadFile);
        finish(PUTUrl(id));
    }

    /**
     * Put Service Without Photo
     *
     * @param login
     * @param id
     * @param name
     * @param price
     * @param description
     * @return
     * @throws Exception
     */
    public String[] PutServiceWithout(String login, int id, String name, String price, String description) throws Exception {
        URL url = PUTUrlWithout(id);
        JSONObject objp = new JSONObject();
        objp.put("nameService", name);
        objp.put("priceService", price);
        objp.put("description", description);
        return SendConnect(login, url, "PUT", objp);
    }

    /**
     * Lista os serviços que não estão associados a uma reparação
     *
     * @param login
     * @param id
     * @return
     * @throws Exception
     */
    public String[][] ShowNotRepairService(String login, int id) throws Exception {
        URL url = new URL(IP() + "/repair/service/" + id);
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

}
