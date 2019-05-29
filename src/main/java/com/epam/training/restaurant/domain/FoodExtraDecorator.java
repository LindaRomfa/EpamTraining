package com.epam.training.restaurant.domain;

public abstract class FoodExtraDecorator implements Food{

    protected Food food;

    public FoodExtraDecorator(Food food){
        this.food = food;
    }

    @Override
    public abstract double calculateHappiness(Client client);

    @Override
    public String toString() {
        return this.food.toString();
    }
}
