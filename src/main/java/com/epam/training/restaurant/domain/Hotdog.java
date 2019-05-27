package com.epam.training.restaurant.domain;

public class Hotdog implements Food {

    private int product;
    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness() + 2;
    }

    @Override
    public String toString() {
        return "Hotdog ";
    }
}
