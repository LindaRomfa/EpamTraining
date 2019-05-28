package com.epam.training.restaurant.domain;

public class Chips implements Food{

    private double effect = 5;

    public double getEffect() {
        return effect;
    }

    @Override
    public void setEffect(double effect) {
        this.effect = effect;
    }

    @Override
    public double calculateHappiness(Client client) {
        effect = percentage(effect);
        return client.getHappiness()*effect;
    }

    private double percentage(double effect) {
        return (100+effect)/100;
    }

    @Override
    public String toString() {
        return "Chips ";
    }
}
