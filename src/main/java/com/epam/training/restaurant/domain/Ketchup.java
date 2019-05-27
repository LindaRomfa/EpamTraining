package com.epam.training.restaurant.domain;

public class Ketchup extends FoodExtraDecorator {

    public Ketchup(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness() * 2;
    }

    public String toString() {
        return "extra = KETCHUP";
    }
}
