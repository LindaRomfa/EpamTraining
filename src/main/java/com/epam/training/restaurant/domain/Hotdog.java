package com.epam.training.restaurant.domain;

public class Hotdog extends AbsFood implements Food {

    private double effect = 2;

    public Hotdog() {
    }

    public double getEffect() {
        return effect;
    }

    @Override
    public void setEffect(double effect) {
        this.effect = effect;
    }

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness() + effect;
    }

    @Override
    public String toString() {
        return "Hotdog ";
    }
}
