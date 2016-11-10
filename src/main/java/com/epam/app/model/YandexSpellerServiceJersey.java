package com.epam.app.model;

import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.pojo.result.SpellError;
import com.epam.app.utils.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static com.epam.app.utils.YandexSpellerUris.*;

/**
 * Created by Aleksei_Voronin on 11/9/2016.
 */
public class YandexSpellerServiceJersey implements YandexSpeller {
    private static final Logger logger = LoggerFactory.getLogger(YandexSpellerServiceJersey.class);

    private static final String PARAMETER_NAME = "text";
    private String host;

    public static YandexSpellerServiceJersey getInstance(String host) {
        return new YandexSpellerServiceJersey(host);
    }

    private YandexSpellerServiceJersey(String host) {
        this.host = host;
    }

    @Override
    public CheckResult checkTextGet(ResultType type, String text) throws IOException, URISyntaxException, JAXBException {
        logger.info("Calling CheckText with GET request for {} response, with '{}' parameter", type, text);
        Client client = ClientBuilder.newClient();
        Response response = client.target(host).path(CHECK_TEXT_METHOD).queryParam(PARAMETER_NAME, text).request(getMediaType(type)).get();
        CheckResult result = performAndParseResult(response, type);
        return result;
    }

    @Override
    public CheckResults checkTextsGet(ResultType type, List<String> params) throws IOException, URISyntaxException, JAXBException {
        logger.info("Calling CheckTexts with GET request for {} response, with '{}' parameters",
                type, Arrays.toString(params.toArray()));
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(host).path(CHECK_TEXTS_METHOD);
        for (String param : params) {
            target = target.queryParam(PARAMETER_NAME, param);
        }
        Response response = target.request(getMediaType(type)).get();
        CheckResults result = performAndParseResults(response, type);
        return result;
    }

    @Override
    public CheckResult checkTextPost(ResultType type, String text) throws IOException, URISyntaxException, JAXBException {
        logger.info("Calling CheckText with POST request for {} response, with '{}' parameter", type, text);
        Client client = ClientBuilder.newClient();
        Form form = new Form(PARAMETER_NAME, text);
        Response response = client.target(host).path(CHECK_TEXT_METHOD).request(getMediaType(type)).post(Entity.form(form));
        CheckResult result = performAndParseResult(response, type);
        return result;
    }

    @Override
    public CheckResults checkTextsPost(ResultType type, List<String> params) throws JAXBException, IOException, URISyntaxException {
        logger.info("Calling CheckTexts with POST request for {} response, with '{}' parameters",
                type, Arrays.toString(params.toArray()));
        Client client = ClientBuilder.newClient();
        Form form = new Form();
        for (String param : params) {
            form.param(PARAMETER_NAME, param);
        }
        Response response = client.target(host).path(CHECK_TEXTS_METHOD).request(getMediaType(type)).post(Entity.form(form));
        CheckResults result = performAndParseResults(response, type);
        return result;
    }

    private String getMediaType(ResultType type) {
        switch (type) {
            case XML:
                return MediaType.APPLICATION_XML;
            case JSON:
                return MediaType.APPLICATION_JSON;
        }
        throw new IllegalArgumentException("The given parameter has unexpected value: " + type);
    }

    private CheckResult performAndParseResult(Response response, ResultType type) {
        CheckResult result = null;
        switch (type) {
            case XML:
                result = response.readEntity(CheckResult.class);
                break;
            case JSON:
                SpellError[] errors = response.readEntity(SpellError[].class);
                result = new CheckResult(errors);
                break;
        }
        return result;
    }

    private CheckResults performAndParseResults(Response response, ResultType type) {
        CheckResults result = null;
        switch (type) {
            case XML:
                result = response.readEntity(CheckResults.class);
                break;
            case JSON:
                SpellError[][] errors = response.readEntity(SpellError[][].class);
                result = new CheckResults(errors);
                break;
        }
        return result;
    }
}
