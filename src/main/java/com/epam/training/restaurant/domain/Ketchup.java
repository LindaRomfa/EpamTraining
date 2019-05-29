package com.epam.training.restaurant.domain;

public class Ketchup extends FoodExtraDecorator {

    private static final double KETCHUPEFFECT = 2;


    public Ketchup(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {

        if (super.food instanceof Hotdog) {
            return client.getHappiness() + Hotdog.HOTDOGEFFECT*KETCHUPEFFECT;
        } else if (super.food instanceof Chips) {
            return client.getHappiness() * percentage(Chips.CHIPSEFFECT*KETCHUPEFFECT);
        }
        return client.getHappiness();

    }

    private double percentage(double effect) {
        return (100 + effect) / 100;
    }


    public String toString() {
        return super.toString() + " extra = KETCHUP ";
    }
}
