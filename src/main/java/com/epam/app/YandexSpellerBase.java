package com.epam.app;

import com.epam.app.pojo.result.CheckResult;
import com.epam.app.pojo.result.CheckResults;
import com.epam.app.utils.ResultType;

@Deprecated
public abstract class YandexSpellerBase {

    public static YandexSpellerBase getInstanse(ResultType resType) {
        YandexSpellerBase result;
        switch (resType) {
            case XML: result = new XmlYandexSpeller();
                break;
            case JSON: result = new JsonYandexSpeller();
                break;
            default: result = null;
                break;
        }

        return result;
    }

    public abstract CheckResult checkTextGet(String text);

    public abstract CheckResult checkTextPost(String text);

    public abstract CheckResults checkTextsGet(String[] text);

    public abstract CheckResults checkTextsPost(String[] text);
}
