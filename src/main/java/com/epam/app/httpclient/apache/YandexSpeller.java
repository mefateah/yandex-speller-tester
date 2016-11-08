package com.epam.app.httpclient.apache;

import com.epam.app.parser.Parser;
import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.utils.ResultType;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Aleksei_Voronin on 11/7/2016.
 */
public class YandexSpeller implements com.epam.app.YandexSpeller {
    private static final String PARAMETER_NAME = "text";
    private String host;

    public static com.epam.app.YandexSpeller getInstance(String host) {
        return new YandexSpeller(host);
    }

    private YandexSpeller(String host) {
        this.host = host;
    }


    @Override
    public CheckResult CheckTextGet(ResultType type, String text) throws IOException, URISyntaxException, JAXBException {
        YandexSpellerClient c = new YandexSpellerClient(host);
        Map<String, String> param = new HashMap<>();
        param.put(PARAMETER_NAME, text);
        String response = c.get(param);
        CheckResult result = Parser.parseResult(type, response);
        return result;
    }

    @Override
    public CheckResults CheckTextsGet(ResultType type, List<String> params) throws IOException, URISyntaxException, JAXBException {
        Map<String, String> nameValueParams = params.stream().collect(Collectors.toMap(i -> PARAMETER_NAME, i -> i));
        YandexSpellerClient c = new YandexSpellerClient(host);
        String response = c.get(nameValueParams);
        CheckResults result = Parser.parseResults(type, response);
        return result;
    }

    @Override
    public CheckResult CheckTextPost(ResultType type, String text) throws IOException, URISyntaxException, JAXBException {
        YandexSpellerClient c = new YandexSpellerClient(host);
        Map<String, String> param = new HashMap<>();
        param.put(PARAMETER_NAME, text);
        String response = c.post(param);
        CheckResult result = Parser.parseResult(type, response);
        return result;
    }

    @Override
    public CheckResults CheckTextsPost(ResultType type, List<String> params) throws JAXBException, IOException, URISyntaxException {
        Map<String, String> nameValueParams = params.stream().collect(Collectors.toMap(i -> PARAMETER_NAME, i -> i));
        YandexSpellerClient c = new YandexSpellerClient(host);
        String response = c.post(nameValueParams);
        CheckResults result = Parser.parseResults(type, response);
        return result;
    }
}
