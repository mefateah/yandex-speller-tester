package com.epam.app;


import com.epam.app.model.YandexSpeller;
import com.epam.app.model.YandexSpellerServiceJersey;
import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.pojo.result.SpellError;
import com.epam.app.utils.ResultType;
import com.epam.app.utils.YandexSpellerUris;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.epam.app.utils.YandexSpellerUris.*;

public class JerseyYandexSpellerTests {

    private CheckResult expectedResult = new CheckResult();
    private CheckResults expectedResults = new CheckResults();
    private final String CHECKING_TEXT = "синхрофазатрон в дубне";
    private final String CHECKING_TEXT2 = "карва";

    @BeforeTest
    public void setup() {
        SpellError se = new SpellError();
        se.setErrorCode(1);
        se.setPos(0);
        se.setRow(0);
        se.setColumn(0);
        se.setWordLength(14);
        se.setWord("синхрофазатрон");
        se.getS().add("синхрофазотрон");
        expectedResult.getSpellErrors().add(se);

        se = new SpellError();
        se.setErrorCode(3);
        se.setPos(17);
        se.setRow(0);
        se.setColumn(17);
        se.setWordLength(5);
        se.setWord("дубне");
        se.getS().add("Дубне");
        expectedResult.getSpellErrors().add(se);

        expectedResults.getCheckResults().add(expectedResult);
        se = new SpellError();
        se.setErrorCode(1);
        se.setPos(0);
        se.setRow(0);
        se.setColumn(0);
        se.setWordLength(5);
        se.setWord("карва");
        se.setS(Arrays.asList(new String[]{"карта", "кара", "корова", "курва", "канва"}));
        expectedResults.getCheckResults().add(new CheckResult(new SpellError[]{se}));
    }

    @Test
    public void checkTextXmlGetTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(XML_SERVICE_URI);
        CheckResult result = service.checkTextGet(ResultType.XML, CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextXmlPostTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(XML_SERVICE_URI);
        CheckResult result = service.checkTextPost(ResultType.XML, CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextJsonGetTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(JSON_SERVICE_URI);
        CheckResult result = service.checkTextGet(ResultType.JSON, CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextJsonPostTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(JSON_SERVICE_URI);
        CheckResult result = service.checkTextPost(ResultType.JSON, CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextsXmlGetTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(XML_SERVICE_URI);
        CheckResults result = service.checkTextsGet(ResultType.XML, Arrays.asList(CHECKING_TEXT, CHECKING_TEXT2));
        Assert.assertEquals(expectedResults, result);
    }

    @Test
    public void checkTextsXmlPostTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(XML_SERVICE_URI);
        CheckResults result = service.checkTextsPost(ResultType.XML, Arrays.asList(CHECKING_TEXT, CHECKING_TEXT2));
        Assert.assertEquals(expectedResults, result);
    }

    @Test
    public void checkTextsJsonGetTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(JSON_SERVICE_URI);
        CheckResults result = service.checkTextsGet(ResultType.JSON, Arrays.asList(CHECKING_TEXT, CHECKING_TEXT2));
        Assert.assertEquals(expectedResults, result);
    }

    @Test
    public void checkTextsJsonPostTest() throws JAXBException, IOException, URISyntaxException {
        YandexSpeller service = YandexSpellerServiceJersey.getInstance(JSON_SERVICE_URI);
        CheckResults result = service.checkTextsPost(ResultType.JSON, Arrays.asList(CHECKING_TEXT, CHECKING_TEXT2));
        Assert.assertEquals(expectedResults, result);
    }
}
