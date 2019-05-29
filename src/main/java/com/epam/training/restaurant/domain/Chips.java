package com.epam.training.restaurant.domain;

public class Chips implements Food{


    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness()*1.05;
    }

    @Override
    public String toString() {
        return "Chips ";
    }
}
