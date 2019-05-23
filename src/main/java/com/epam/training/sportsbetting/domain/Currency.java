package com.epam.training.sportsbetting.domain;

public enum Currency {
    HUF("HUF"),
    EUR("EUR"),
    USD("USD");

    private String value;

    Currency(String value){
        this.value = value;
    }
}
