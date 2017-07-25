/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import javax.swing.*;
import java.net.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Convite
 */
public class PostService {

    private static String LINE_FEED = "\r\n";

    Ip ip = new Ip();

    public void PostService(String login, String name, String price, String description, File photo) throws Exception {
        JSONObject newjson = (JSONObject) new JSONParser().parse(login);
        String user = newjson.get("login").toString();
        String pass = newjson.get("password").toString();
        byte[] message = (user + ":" + pass).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);

        URL url = new URL(ip.stIp() + "/service");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs
        String boundary = Long.toHexString(System.currentTimeMillis());
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Accept", "multipart/form-data");
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            String fileName = photo.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: text/plain").append(LINE_FEED);
            writer.append(LINE_FEED);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(photo), "UTF-8"));
                for (String line; (line = reader.readLine()) != null;) {
                    writer.append(line).append(LINE_FEED);
                }
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException logOrIgnore) {
                    }
                }
            }
            writer.append(LINE_FEED);
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.append("Exception writing file" + e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
}
