package com.surveywizz.surveyservice.enums;

public enum Company {
    Trendyol("Trendyol"),
    Hepsiburada("Hepsiburada"),
    N11("N11"),
    Amazon("Amazon"),
    Alibaba("Alibaba"),
    Getir("Getir"),
    Dolap("Dolap");

    final String text;
    Company(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
