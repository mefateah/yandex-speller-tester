package com.epam.app.parser;

import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.pojo.result.SpellError;
import com.epam.app.utils.ResultType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Aleksei_Voronin on 10/28/2016.
 */
public class Parser {
    public static CheckResult parseResult(ResultType resultType, String response) throws JAXBException, IOException {
        CheckResult result = null;
        switch (resultType) {
            case XML:
                result = parseXml(response, CheckResult.class);
                break;
            case JSON:
                result = parseJsonResult(response);
                break;
        }
        return result;
    }

    public static CheckResults parseResults(ResultType resultType, String response) throws JAXBException, IOException {
        CheckResults result = null;
        switch (resultType) {
            case XML:
                result = parseXml(response, CheckResults.class);
                break;
            case JSON:
                result = parseJsonResults(response);
                break;
        }
        return result;
    }

    private static <T> T parseXml(String responce, Class<T> type) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(new ByteArrayInputStream(responce.replaceAll("[\n\r]", "").getBytes()));
        return result;
    }

    private static CheckResult parseJsonResult(String responce) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SpellError[] errors = mapper.readValue(responce, SpellError[].class);
        CheckResult r = new CheckResult(errors);
        return r;
    }

    private static CheckResults parseJsonResults(String responce) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SpellError[][] errors = mapper.readValue(responce, SpellError[][].class);
        CheckResults r = new CheckResults(errors);
        return r;
    }

}
