package com.epam.training.restaurant.domain;

public class Mustard extends FoodExtraDecorator {

    private static double effect = 1;

    public Mustard(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness() + effect;
    }

    public String toString() {

        return super.toString() + " extra = MUSTARD ";
    }
}
