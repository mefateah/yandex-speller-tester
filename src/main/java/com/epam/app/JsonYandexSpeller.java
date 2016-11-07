package com.epam.app;

import com.epam.app.result.CheckResult;
import com.epam.app.result.CheckResults;
import com.epam.app.result.SpellError;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

/**
 * Created by Aleksei_Voronin on 10/27/2016.
 */
class JsonYandexSpeller extends YandexSpellerBase {
    private String baseUri = "http://speller.yandex.net/services/spellservice.json";
    private String mediaType = MediaType.APPLICATION_JSON;
    private String charset = "UTF-8";

    public CheckResult checkTextGet(String text) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkText").queryParam("text", text);
        SpellError[] errors = target.request(mediaType).get(SpellError[].class);
        CheckResult result = new CheckResult();
        result.getSpellErrors().addAll(Arrays.asList(errors));
        return result;
    }

    public CheckResult checkTextPost(String text) {
        Client client = ClientBuilder.newClient();
        Form form = new Form("text", text);
        SpellError[] errors = client.target(baseUri).path("checkText").request(mediaType).post(Entity.form(form), SpellError[].class);
        CheckResult result = new CheckResult();
        result.getSpellErrors().addAll(Arrays.asList(errors));
        return result;
    }

    public CheckResults checkTextsGet(String[] text) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkTexts");
        for (String param : text) {
            target = target.queryParam("text", param);
        }
        SpellError[][] result = target.request(mediaType).get(SpellError[][].class);
        return new CheckResults(result);
    }

    public CheckResults checkTextsPost(String[] text) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUri).path("checkTexts");
        Form form = new Form();
        for (String param : text) {
            form.param("text", param);
        }
        SpellError[][] result = target.request(mediaType).post(Entity.form(form), SpellError[][].class);
        return new CheckResults(result);
    }
}
