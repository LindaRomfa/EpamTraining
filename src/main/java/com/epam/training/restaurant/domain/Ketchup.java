package com.epam.training.restaurant.domain;

public class Ketchup extends FoodExtraDecorator {

    private double effect = 2;


    public Ketchup(Food food) {
        super(food);
    }

    @Override
    public double getEffect() {
        return super.getEffect();
    }

    @Override
    public void setEffect(double effect) {
        super.setEffect(getEffect()*effect);
    }

    @Override
    public double calculateHappiness(Client client) {
        setEffect(effect);
        return super.calculateHappiness(client);
    }

    public String toString() {
        return super.toString() + " extra = KETCHUP ";
    }
}
