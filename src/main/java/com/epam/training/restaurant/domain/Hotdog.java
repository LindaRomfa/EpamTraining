package com.epam.training.restaurant.domain;

public class Hotdog implements Food {

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness() + 2;
    }

    @Override
    public String toString() {
        return "Hotdog ";
    }
}
