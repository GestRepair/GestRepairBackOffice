/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import java.net.*;
import java.io.*;
import java.awt.image.*;
import static java.lang.Math.log;
import static java.lang.StrictMath.log;
import javax.imageio.*;

import static java.lang.System.currentTimeMillis;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.URLConnection.guessContentTypeFromName;
import static java.text.MessageFormat.format;

import static java.util.logging.Level.INFO;
import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;
import javax.swing.*;

import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeMath.log;
import static jdk.nashorn.internal.objects.NativeMath.log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author Convite
 */
public class PostService {

    private static String LINE_FEED = "\r\n";
    private static final String CHARSET = "UTF-8";
    private long start;

    Ip ip = new Ip();

    public URL Url() throws MalformedURLException {
        URL url = new URL(ip.stIp() + "/service");
        return url;
    }

    HttpURLConnection connection;
    OutputStream outputStream;
    PrintWriter writer;
    String boundary;

    public void PostService(String login, String name, String price, String description, File photo) throws Exception {
        //URL url = new URL(ip.stIp() + "/service");

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

            log(INFO, format("{0} took {4} ms", Url(), (currentTimeMillis() - start)));
            return bytes.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
}
