package com.epam.training.restaurant.domain;

public class ExtraFood implements Food{
    @Override
    public double getEffect() {
        return 0;
    }

    @Override
    public void setEffect(double effect) {

    }

    @Override
    public double calculateHappiness(Client client) {
        return 0;
    }
}
