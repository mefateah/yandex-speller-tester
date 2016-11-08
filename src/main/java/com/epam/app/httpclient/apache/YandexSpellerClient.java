package com.epam.app.httpclient.apache;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksei_Voronin on 11/2/2016.
 */
public class YandexSpellerClient {
    private String baseHost;

    public YandexSpellerClient(String host) {
        baseHost = host;
    }

    public String get(Map<String, String> params) throws URISyntaxException, IOException {
        List<NameValuePair> p = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            p.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        URIBuilder builder = new URIBuilder(baseHost).addParameters(p);
        HttpResponse response = Request.Get(builder.build()).execute().returnResponse();
        System.out.println(response.getStatusLine());
        return EntityUtils.toString(response.getEntity());
    }

    public String post(Map<String, String> params) throws URISyntaxException, IOException {
        List<NameValuePair> p = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            p.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        URIBuilder builder = new URIBuilder(baseHost);
        HttpResponse response = Request.Post(builder.build()).bodyForm(p).execute().returnResponse();
        System.out.println(response.getStatusLine());
        return EntityUtils.toString(response.getEntity());
    }
}
