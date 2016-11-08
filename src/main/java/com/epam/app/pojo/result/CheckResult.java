package com.epam.app.pojo.result;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aleksei_Voronin on 10/27/2016.
 */
@XmlRootElement(name = "SpellResult")
public class CheckResult {

    @XmlElement(name = "error")
    private List<SpellError> spellErrors = new ArrayList<>();

    public List<SpellError> getSpellErrors() {
        return spellErrors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckResult result = (CheckResult) o;

        return spellErrors != null ? spellErrors.equals(result.spellErrors) : result.spellErrors == null;

    }

    @Override
    public int hashCode() {
        return spellErrors != null ? spellErrors.hashCode() : 0;
    }

    public CheckResult() {
    }

    public CheckResult(SpellError[] spellErrors) {
        this.spellErrors = Arrays.asList(spellErrors);
    }
}
