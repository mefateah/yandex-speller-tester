package com.epam.app.pojo.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksei_Voronin on 11/1/2016.
 */
@XmlRootElement(name = "ArrayOfSpellResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckResults {

    @XmlElement(name = "SpellResult")
    private List<CheckResult> checkResults = new ArrayList<>();

    public List<CheckResult> getCheckResults() {
        return checkResults;
    }

    public void setCheckResults(List<CheckResult> checkResults) {
        this.checkResults = checkResults;
    }

    public CheckResults() {
    }

    public CheckResults(SpellError[][] errorsSet) {
        for (SpellError[] errors : errorsSet) {
            checkResults.add(new CheckResult(errors));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckResults that = (CheckResults) o;

        return checkResults != null ? checkResults.equals(that.checkResults) : that.checkResults == null;

    }

    @Override
    public int hashCode() {
        return checkResults != null ? checkResults.hashCode() : 0;
    }
}
