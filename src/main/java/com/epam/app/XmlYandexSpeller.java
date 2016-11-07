package com.epam.app;

import com.epam.app.result.CheckResult;
import com.epam.app.result.CheckResults;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Created by Aleksei_Voronin on 10/27/2016.
 * TODO: Ask about exception handling
 * TODO: exclude node names from code
 */
class XmlYandexSpeller extends YandexSpellerBase {
    private String baseUri = "http://speller.yandex.net/services/spellservice";
    private String mediaType = MediaType.APPLICATION_XML;
    private String charset = "UTF-8";

    public CheckResult checkTextGet(String text) {
        /*
        HttpClient client = new HttpClient();
        HttpResponse response = client.sendGetRequest(baseUri, text);
        CheckResult result = new Parser().parse(response.getText(), charset);
        return result;
        */
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkText").queryParam("text", text);
        CheckResult result = target.request(mediaType).get(CheckResult.class);
        return result;
    }

    public CheckResult checkTextPost(String text) {
        /*
        HttpClient client = new HttpClient();
        HashMap<String, String> query = new HashMap<>();
        query.put("text", text);
        client.sendPostRequest(baseUri, query, charset);
        //parser.parse(response);
        return new CheckResult();
        */
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkText");
        Form form = new Form("text", text);
        CheckResult result = target.request(mediaType).post(Entity.form(form), CheckResult.class);
        return result;
    }

    public CheckResults checkTextsGet(String[] text) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkTexts");
        for (String param : text) {
            target = target.queryParam("text", param);
        }
        CheckResults result = target.request(mediaType).get(CheckResults.class);
        return result;
    }

    public CheckResults checkTextsPost(String[] text) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkTexts");
        Form form = new Form();
        for (String param : text) {
            form.param("text", param);
        }
        CheckResults result = target.request(mediaType).post(Entity.form(form), CheckResults.class);
        return result;
    }
}
