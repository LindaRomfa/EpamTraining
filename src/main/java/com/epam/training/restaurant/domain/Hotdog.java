package com.epam.training.restaurant.domain;

public class Hotdog implements Food {

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness();
    }

    @Override
    public String toString() {
        return "Hotdog ";
    }
}
