package com.epam.app.httpclient.apache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aleksei_Voronin on 11/2/2016.
 */
public class YandexSpeller {
    private String baseHost;
    private static final String PARAM_NAME = "text";

    public YandexSpeller(String host) {
        baseHost = host;
    }

    public String checkTextGet(String param) throws IOException, URISyntaxException {
        // Native API
        URIBuilder builder = new URIBuilder(baseHost).setParameter(PARAM_NAME, param);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(builder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            response.close();
        }
    }

    public String checkTextPost(String param) throws URISyntaxException, IOException {
        // Fluent API
        URIBuilder builder = new URIBuilder(baseHost);
        HttpResponse response = Request.Post(builder.build()).bodyForm(new BasicNameValuePair(PARAM_NAME, param)).execute().returnResponse();
        System.out.println(response.getStatusLine());
        return EntityUtils.toString(response.getEntity());
    }
}
