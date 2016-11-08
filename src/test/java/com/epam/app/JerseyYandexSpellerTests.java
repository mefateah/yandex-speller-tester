package com.epam.app;


import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.pojo.result.SpellError;
import com.epam.app.utils.ResultType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

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
    public void checkTextXmlGetTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.XML);
        // TODO: Consider using TextFormat.Plain parameter
        CheckResult result = service.checkTextGet(CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextXmlPostTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.XML);
        CheckResult result = service.checkTextPost(CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextJsonGetTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.JSON);
        CheckResult result = service.checkTextGet(CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextJsonPostTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.JSON);
        CheckResult result = service.checkTextPost(CHECKING_TEXT);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkTextsXmlGetTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.XML);
        CheckResults result = service.checkTextsGet(new String[]{CHECKING_TEXT, CHECKING_TEXT2});
        Assert.assertEquals(expectedResults, result);
    }

    @Test
    public void checkTextsXmlPostTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.XML);
        CheckResults result = service.checkTextsPost(new String[]{CHECKING_TEXT, CHECKING_TEXT2});
        Assert.assertEquals(expectedResults, result);
    }

    @Test
    public void checkTextsJsonGetTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.JSON);
        CheckResults result = service.checkTextsGet(new String[]{CHECKING_TEXT, CHECKING_TEXT2});
        Assert.assertEquals(expectedResults, result);
    }

    @Test
    public void checkTextsJsonPostTest() {
        YandexSpellerBase service = YandexSpellerBase.getInstanse(ResultType.JSON);
        CheckResults result = service.checkTextsPost(new String[]{CHECKING_TEXT, CHECKING_TEXT2});
        Assert.assertEquals(expectedResults, result);
    }
}
