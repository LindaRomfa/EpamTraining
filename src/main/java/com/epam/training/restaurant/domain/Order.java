package com.epam.training.restaurant.domain;

import com.epam.training.restaurant.observer.Observable;

import java.util.List;

public class Order extends Observable {
    private String food;
    private List<String> extras;
    private Client client;

    public Order(String food, List<String> extras, Client client) {
        this.food = food;
        this.extras = extras;
        this.client = client;
        this.addObserver(client);
    }

    public String getFood() {
        return food;
    }

    public List<String> getExtras() {
        return extras;
    }

    @Override
    public String toString() {
        return "Order[" +
                "food='" + food + '\'' +
                ", extras=" + extras +
                ']';
    }
}
