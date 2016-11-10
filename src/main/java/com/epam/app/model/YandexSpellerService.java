package com.epam.app.model;

import com.epam.app.httpclient.apache.HttpRequestSender;
import com.epam.app.parser.Parser;
import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.utils.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.app.utils.YandexSpellerUris.*;

/**
 * Created by Aleksei_Voronin on 11/7/2016.
 *
 * Apache Http Client
 * Jackson (JSON parsing)
 * JAXB (XML parsing)
 */
public class YandexSpellerService implements YandexSpeller {
    private static final Logger logger = LoggerFactory.getLogger(YandexSpellerService.class);

    private static final String PARAMETER_NAME = "text";
    private String host;

    public static YandexSpeller getInstance(String host) {
        return new YandexSpellerService(host);
    }

    private YandexSpellerService(String host) {
        this.host = host;
    }

    private String buildServiceUrl(String methodName) throws MalformedURLException {
        return new URL(new URL(host), methodName).toString();
    }

    @Override
    public CheckResult checkTextGet(ResultType type, String text) throws IOException, URISyntaxException, JAXBException {
        logger.info("Calling CheckText with GET request for {} response, with '{}' parameter", type, text);
        HttpRequestSender c = new HttpRequestSender(buildServiceUrl(CHECK_TEXT_METHOD));
        Map<String, String> param = new HashMap<>();
        param.put(PARAMETER_NAME, text);
        String response = c.get(param);
        CheckResult result = Parser.parseResult(type, response);
        return result;
    }

    @Override
    public CheckResults checkTextsGet(ResultType type, List<String> params) throws IOException, URISyntaxException, JAXBException {
        logger.info("Calling CheckTexts with GET request for {} response, with '{}' parameters",
                type, Arrays.toString(params.toArray()));
        Map<String, String> nameValueParams = params.stream().collect(Collectors.toMap(i -> PARAMETER_NAME, i -> i));
        HttpRequestSender c = new HttpRequestSender(buildServiceUrl(CHECK_TEXTS_METHOD));
        String response = c.get(nameValueParams);
        CheckResults result = Parser.parseResults(type, response);
        return result;
    }

    @Override
    public CheckResult checkTextPost(ResultType type, String text) throws IOException, URISyntaxException, JAXBException {
        logger.info("Calling CheckText with POST request for {} response, with '{}' parameter", type, text);
        HttpRequestSender c = new HttpRequestSender(buildServiceUrl(CHECK_TEXT_METHOD));
        Map<String, String> param = new HashMap<>();
        param.put(PARAMETER_NAME, text);
        String response = c.post(param);
        CheckResult result = Parser.parseResult(type, response);
        return result;
    }

    @Override
    public CheckResults checkTextsPost(ResultType type, List<String> params) throws JAXBException, IOException, URISyntaxException {
        logger.info("Calling CheckTexts with POST request for {} response, with '{}' parameters",
                type, Arrays.toString(params.toArray()));
        Map<String, String> nameValueParams = params.stream().collect(Collectors.toMap(i -> PARAMETER_NAME, i -> i));
        HttpRequestSender c = new HttpRequestSender(buildServiceUrl(CHECK_TEXTS_METHOD));
        String response = c.post(nameValueParams);
        CheckResults result = Parser.parseResults(type, response);
        return result;
    }
}
