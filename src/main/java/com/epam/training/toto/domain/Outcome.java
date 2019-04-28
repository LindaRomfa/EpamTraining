package com.epam.training.toto.domain;

public enum Outcome {

    _1("1"),
    _2("2"),
    x("x");

    private String value;
    Outcome(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public void setValue(String value){
        this.value = value;
    }
}
