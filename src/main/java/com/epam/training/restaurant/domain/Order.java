package com.epam.training.restaurant.domain;

import java.util.List;

public class Order {
    private String food;
    private List<String> extras;

    public Order(String food, List<String> extras) {
        this.food = food;
        this.extras = extras;
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
