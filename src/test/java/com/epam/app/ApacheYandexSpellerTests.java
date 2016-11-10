package com.epam.app;

import com.epam.app.model.YandexSpeller;
import com.epam.app.model.YandexSpellerService;
import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.pojo.result.SpellError;
import com.epam.app.utils.ResultType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static com.epam.app.utils.YandexSpellerUris.JSON_SERVICE_URI;
import static com.epam.app.utils.YandexSpellerUris.XML_SERVICE_URI;

/**
 * Created by Aleksei_Voronin on 11/3/2016.
 */
public class ApacheYandexSpellerTests {
    private static final String TEST_WORD = "hillo";

    /*
    private static final String JSON_ANSWER = "[{\"code\":1,\"pos\":0,\"row\":0,\"col\":0,\"len\":5,\"word\":\"hillo\",\"s\":[\"hello\"]}]";
    private static final String XML_ANSWER = "<?xml version=\"1.0\" encoding=\"utf-8\"?><SpellResult><error code=\"1\" pos=\"0\" row=\"0\" col=\"0\" len=\"5\"><word>hillo</word><s>hello</s></error></SpellResult>";
    */

    private CheckResult expectedResult;
    private CheckResults expectedResults;

    @BeforeTest
    public void setup() {
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
        YandexSpeller speller = YandexSpellerService.getInstance(XML_SERVICE_URI);
        CheckResult result = speller.checkTextGet(ResultType.XML, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextGetJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(JSON_SERVICE_URI);
        CheckResult result = speller.checkTextGet(ResultType.JSON, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextPostXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(XML_SERVICE_URI);
        CheckResult result = speller.checkTextPost(ResultType.XML, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextPostJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(JSON_SERVICE_URI);
        CheckResult result = speller.checkTextPost(ResultType.JSON, TEST_WORD);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void CheckTextsGetXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(XML_SERVICE_URI);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.checkTextsGet(ResultType.XML, params);
        Assert.assertEquals(results, expectedResults);
    }

    @Test
    public void CheckTextsGetJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(JSON_SERVICE_URI);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.checkTextsGet(ResultType.JSON, params);
        Assert.assertEquals(results, expectedResults);
    }

    @Test
    public void CheckTextsPostXml() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(XML_SERVICE_URI);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.checkTextsPost(ResultType.XML, params);
        Assert.assertEquals(results, expectedResults);
    }

    @Test
    public void CheckTextsPostJson() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller speller = YandexSpellerService.getInstance(JSON_SERVICE_URI);
        List<String> params = Arrays.asList(TEST_WORD);
        CheckResults results = speller.checkTextsPost(ResultType.JSON, params);
        Assert.assertEquals(results, expectedResults);
    }
}
