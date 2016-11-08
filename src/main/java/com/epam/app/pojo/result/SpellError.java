package com.epam.app.pojo.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksei_Voronin on 10/27/2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellError {
    @XmlAttribute(name = "code")
    @JsonProperty("code")
    private int errorCode;
    @XmlAttribute(name = "pos")
    private int pos;
    @XmlAttribute(name = "row")
    private int row;
    @XmlAttribute(name = "col")
    @JsonProperty("col")
    private int column;
    @XmlElement(name = "word")
    private String word;
    @XmlAttribute(name = "len")
    @JsonProperty("len")
    private int wordLength;
    @XmlElement(name = "s")
    private List<String> s = new ArrayList<>();

    public List<String> getS() {
        return s;
    }

    public void setS(List<String> s) {
        this.s = s;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getWord() {
        return word;
    }

    public int getWordLength() {
        return wordLength;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpellError that = (SpellError) o;

        if (errorCode != that.errorCode) return false;
        if (pos != that.pos) return false;
        if (row != that.row) return false;
        if (column != that.column) return false;
        if (wordLength != that.wordLength) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        return s != null ? s.equals(that.s) : that.s == null;
    }

    @Override
    public int hashCode() {
        int h = 31;
        int result = errorCode;
        result = h * result + pos;
        result = h * result + row;
        result = h * result + column;
        result = h * result + (word != null ? word.hashCode() : 0);
        result = h * result + wordLength;
        result = h * result + (s != null ? s.hashCode() : 0);
        return result;
    }
}
