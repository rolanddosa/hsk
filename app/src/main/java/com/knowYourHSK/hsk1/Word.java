package com.knowYourHSK.hsk1;

import java.io.Serializable;

public class Word implements Serializable {
    public Word(String label, String pinyin, String meaning) {
        this.label = label;
        this.pinyin = pinyin;
        this.meaning = meaning;
    }

    private String label;
    private String pinyin;
    private String meaning;

    public String getLabel() {
        return label;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getMeaning() {
        return meaning;
    }
}
