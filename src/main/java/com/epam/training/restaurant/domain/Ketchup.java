package com.epam.training.restaurant.domain;

public class Ketchup extends FoodExtraDecorator {

    private static final double KETCHUPEFFECT = 2;
    private static double chipsEffect = 5;
    private static double hotdogEffect = 2;


    public Ketchup(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {

        if (super.food instanceof Hotdog) {
            hotdogEffect *= KETCHUPEFFECT;
            return super.calculateHappiness(client) + hotdogEffect;
        } else if (super.food instanceof Chips) {
            chipsEffect *= KETCHUPEFFECT;
            return super.calculateHappiness(client) * percentage(chipsEffect);
        }
        return super.calculateHappiness(client);

    }

    private double percentage(double effect) {
        return (100 + effect) / 100;
    }


    public String toString() {
        return super.toString() + " extra = KETCHUP ";
    }
}
