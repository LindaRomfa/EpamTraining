package com.epam.training.restaurant.domain;

public class ExtraFood implements Food{
    @Override
    public double calculateHappiness(Client client) {
        return 0;
    }
}
