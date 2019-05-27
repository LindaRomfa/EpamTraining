package com.epam.training.restaurant.domain;

public class Ketchup extends FoodExtraDecorator {

    public Ketchup(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {
        super.calculateHappiness(client);
        return super.calculateHappiness(client)*2;
    }

    public String toString() {
        return super.toString() + " extra = KETCHUP ";
    }
}
