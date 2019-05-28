package com.epam.training.restaurant.domain;

public class Mustard extends FoodExtraDecorator {

    private static double effect = 1;

    public Mustard(Food food) {
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
        setEffect(0);
        return super.calculateHappiness(client) + effect;
    }

    public String toString() {

        return super.toString() + " extra = MUSTARD ";
    }
}
