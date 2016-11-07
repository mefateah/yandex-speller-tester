package com.epam.app;

import com.epam.app.httpclient.apache.YandexSpeller;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Aleksei_Voronin on 11/3/2016.
 */
public class ApacheHttpClientUnitTests {
    private static final String TEST_WORD = "hillo";
    private static final String JSON_ANSWER = "[{\"code\":1,\"pos\":0,\"row\":0,\"col\":0,\"len\":5,\"word\":\"hillo\",\"s\":[\"hello\"]}]";
    private static final String XML_ANSWER = "<?xml version=\"1.0\" encoding=\"utf-8\"?><SpellResult><error code=\"1\" pos=\"0\" row=\"0\" col=\"0\" len=\"5\"><word>hillo</word><s>hello</s></error></SpellResult>";
    private static final String JSON_HOST = "http://speller.yandex.net/services/spellservice.json/checkText";
    private static final String XML_HOST = "http://speller.yandex.net/services/spellservice/checkText";

    @Test
    public void SpellCheckerGet() throws IOException, URISyntaxException {
        YandexSpeller ys = new YandexSpeller(JSON_HOST);
        String result = ys.checkTextGet(TEST_WORD);
        System.out.println(result);
        Assert.assertEquals(result, JSON_ANSWER);
    }

    @Test
    public void SpellCheckerPost() throws IOException, URISyntaxException {
        YandexSpeller ys = new YandexSpeller(JSON_HOST);
        String result = ys.checkTextPost(TEST_WORD);
        System.out.println(result);
        Assert.assertEquals(result, JSON_ANSWER);
    }

    @Test
    public void SpellCheckerGetXml() throws IOException, URISyntaxException {
        YandexSpeller ys = new YandexSpeller(XML_HOST);
        String result = ys.checkTextGet(TEST_WORD);
        System.out.println(result);
        Assert.assertEquals(result.replaceAll("[\n\r]", ""), XML_ANSWER);
    }

    @Test
    public void SpellCheckerPostXml() throws IOException, URISyntaxException {
        YandexSpeller ys = new YandexSpeller(XML_HOST);
        String result = ys.checkTextPost(TEST_WORD);
        System.out.println(result);
        Assert.assertEquals(result.replaceAll("[\n\r]", ""), XML_ANSWER);
    }
}
