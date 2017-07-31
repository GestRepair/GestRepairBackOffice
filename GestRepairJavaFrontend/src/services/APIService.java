/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ip.Connect;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class APIService {
    
    private static final String LINE_FEED = "\r\n";
    private static final String CHARSET = "UTF-8";
    private long start;

    public URL Url() throws MalformedURLException {
        URL url = new URL(connect.IP() + "/service");
        return url;
    }

    OutputStream outputStream;
    PrintWriter writer;
    String boundary;
    HttpURLConnection connection;
    Connect connect = new Connect();
    public void conn(String login,URL url,String method) throws ParseException, IOException{
        JSONObject newjson = (JSONObject) new JSONParser().parse(login);
        String user = newjson.get("login").toString();
        String pass = newjson.get("password").toString();
        byte[] message = (user + ":" + pass).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
    }
    
    public void PostService(String login, String name, String price, String description, File photo) throws Exception {
        //URL url = new URL(connect.IP() + "/service");

        final File uploadFile = photo;
        try {
            Connection(login);
            addFormField("nameService", name);
            addFormField("priceService", price);
            addFormField("description", description);
            addFilePart("photo", uploadFile);
            final byte[] bytes = finish();
            //final OutputStream os = new FileOutputStream("someoutput.txt");
            //os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Connection(String login) throws UnsupportedEncodingException, ParseException, IOException {

        start = currentTimeMillis();

        boundary = "---------------------------" + currentTimeMillis();

        JSONObject newjson = (JSONObject) new JSONParser().parse(login);
        String user = newjson.get("login").toString();
        String pass = newjson.get("password").toString();
        byte[] message = (user + ":" + pass).getBytes(CHARSET);
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        connection = (HttpURLConnection) Url().openConnection();
        connection.setConnectTimeout(15000);//15 secs
        connection.setReadTimeout(10000);//10 secs
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", CHARSET);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        outputStream = connection.getOutputStream();

        writer = new PrintWriter(new OutputStreamWriter(outputStream, CHARSET), true);
    }

    public void addFormField(final String name, final String value) {
        writer.append("--").append(boundary).append(LINE_FEED)
                .append("Content-Disposition: form-data; name=\"").append(name)
                .append("\"").append(LINE_FEED)
                .append("Content-Type: text/plain; charset=").append(CHARSET)
                .append(LINE_FEED).append(LINE_FEED).append(value).append(LINE_FEED);
    }

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

    public void addHeaderField(String name, String value) {
        writer.append(name).append(": ").append(value).append(LINE_FEED);
    }

    public byte[] finish() throws IOException {
        writer.append(LINE_FEED).append("--").append(boundary).append("--")
                .append(LINE_FEED);
        writer.close();

        final int status = connection.getResponseCode();
        if (status != HTTP_OK) {
            throw new IOException(format("{0} failed with HTTP status: {1}",
                    Url(), status));
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
    public String getService(String login) throws MalformedURLException, IOException, ParseException {
        URL url = new URL(connect.IP() + "/service/comp");
        conn(login,url,"GET");
        
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
        } // or StringBuffer if Java version 5+
        connection.disconnect();
        return json;
    }
    @SuppressWarnings("empty-statement")
    public String[][] ListService(String list) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][5];
            for (int i = 0; i < data.size(); i++) {
                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idService") + "";
                dataTable[i][1] = (String) datas.get("nameService");
                dataTable[i][2] = (long) datas.get("priceService")+"";
                dataTable[i][3] = (String) datas.get("description");
                dataTable[i][4] = (String) datas.get("photo");
            };
            return dataTable;

        } catch (ParseException pe) {
            System.out.println("Erro");
            return null;
        }
    }
    public String[][] Service (String login) throws IOException, ParseException{
        return ListService(getService(login));
    }

}
