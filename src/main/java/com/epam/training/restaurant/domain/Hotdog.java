package com.epam.training.restaurant.domain;

public class Hotdog implements Food {

    protected static final double HOTDOGEFFECT = 2;

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness()+HOTDOGEFFECT;
    }

    @Override
    public String toString() {
        return "Hotdog ";
    }
}
