package com.epam.app.httpclient.standart;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksei_Voronin on 10/28/2016.
 */
public class HttpClient {

    public HttpResponse sendGetRequest(String baseUri, String text) {
        HttpURLConnection httpConnection = null;
        InputStream response = null;

        try {
            String charset = "UTF-8";
            String query = String.format("text=%s", URLEncoder.encode(text, charset));

            URLConnection connection = new URL(baseUri + "?" + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
            httpConnection = (HttpURLConnection)connection;
            System.out.println(httpConnection.getResponseCode());

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }

        return new HttpResponse();
    }

    public InputStream sendPostRequest(String url, HashMap<String, String> params, String charset) {
        InputStream response = null;
        StringBuffer queries = new StringBuffer();

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                queries.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), charset) + "&");
            }

            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Context-Type", "application/x-www-form-urlencoded;charset=" + charset);
            OutputStream output = connection.getOutputStream();
            output.write(queries.toString().getBytes(charset));
            response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }

            System.out.println("----- headers ------");
            for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
                System.out.println(header.getKey() + "=" + header.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
