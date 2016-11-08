package com.epam.app;

import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.utils.ResultType;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Aleksei_Voronin on 11/7/2016.
 */
public interface YandexSpeller {
    CheckResult CheckTextGet(ResultType type, String text) throws IOException, URISyntaxException, JAXBException;
    CheckResults CheckTextsGet(ResultType type, List<String> params) throws IOException, URISyntaxException, JAXBException;
    CheckResult CheckTextPost(ResultType type, String text) throws IOException, URISyntaxException, JAXBException;
    CheckResults CheckTextsPost(ResultType type, List<String> params) throws JAXBException, IOException, URISyntaxException;
}
