package com.epam.app;

import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.pojo.result.SpellError;
import com.epam.app.utils.ResultType;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Aleksei_Voronin on 11/3/2016.
 */
public class ApacheYandexSpellerTests {
    private static final String TEST_WORD = "hillo";

    private static final String JSON_ANSWER = "[{\"code\":1,\"pos\":0,\"row\":0,\"col\":0,\"len\":5,\"word\":\"hillo\",\"s\":[\"hello\"]}]";
    private static final String XML_ANSWER = "<?xml version=\"1.0\" encoding=\"utf-8\"?><SpellResult><error code=\"1\" pos=\"0\" row=\"0\" col=\"0\" len=\"5\"><word>hillo</word><s>hello</s></error></SpellResult>";

    private static final String JSON_HOST = "http://speller.yandex.net/services/spellservice.json/checkText";
    private static final String JSON_HOST2 = "http://speller.yandex.net/services/spellservice.json/checkTexts";
    private static final String XML_HOST = "http://speller.yandex.net/services/spellservice/checkText";
    private static final String XML_HOST2 = "http://speller.yandex.net/services/spellservice/checkTexts";

    private CheckResult expectedResult;
    private CheckResults expectedResults;

    public ApacheYandexSpellerTests() {
        SpellError error = new SpellError();
        error.setErrorCode(1);
        error.setPos(0);
        error.setRow(0);
        error.setColumn(0);
        error.setWordLength(5);
        error.setWord(TEST_WORD);
        error.getS().add("hello");

        expectedResult = new CheckResult(new SpellError[]{error});
        expectedResults = new CheckResults(new SpellError[][]{new SpellError[]{error}});
    }

    @Test
    public void CheckTextGetXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(XML_HOST);
        CheckResult result = speller.CheckTextGet(ResultType.XML, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextGetJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(JSON_HOST);
        CheckResult result = speller.CheckTextGet(ResultType.JSON, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextPostXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(XML_HOST);
        CheckResult result = speller.CheckTextPost(ResultType.XML, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextPostJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(JSON_HOST);
        CheckResult result = speller.CheckTextPost(ResultType.JSON, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextsGetXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(XML_HOST2);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.CheckTextsGet(ResultType.XML, params);
        Assert.assertEquals(results, expectedResults);
    }

    @Test
    public void CheckTextsGetJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(JSON_HOST2);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.CheckTextsGet(ResultType.JSON, params);
        Assert.assertEquals(results, expectedResults);
    }

    @Test
    public void CheckTextsPostXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(XML_HOST2);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.CheckTextsPost(ResultType.XML, params);
        Assert.assertEquals(results, expectedResults);
    }

    @Test
    public void CheckTextsPostJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = com.epam.app.httpclient.apache.YandexSpeller.getInstance(JSON_HOST2);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.CheckTextsPost(ResultType.JSON, params);
        Assert.assertEquals(results, expectedResults);
    }
}
