package com.epam.training.restaurant.domain;

public class Chips implements Food{

    protected static final double CHIPSEFFECT = 1.05;

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness()*CHIPSEFFECT;
    }

    @Override
    public String toString() {
        return "Chips ";
    }
}
