package com.epam.app.model;

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
    CheckResult checkTextGet(ResultType type, String text) throws IOException, URISyntaxException, JAXBException;
    CheckResults checkTextsGet(ResultType type, List<String> params) throws IOException, URISyntaxException, JAXBException;
    CheckResult checkTextPost(ResultType type, String text) throws IOException, URISyntaxException, JAXBException;
    CheckResults checkTextsPost(ResultType type, List<String> params) throws JAXBException, IOException, URISyntaxException;
}
