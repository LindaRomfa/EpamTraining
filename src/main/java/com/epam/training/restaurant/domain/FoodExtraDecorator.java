package com.epam.training.restaurant.domain;

public abstract class FoodExtraDecorator implements Food{

    private Food food;

    public double getEffect() {
        return food.getEffect();
    }

    public void setEffect(double effect){
        food.setEffect(effect);
    }

    public FoodExtraDecorator(Food food){
        this.food = food;
    }

    @Override
    public double calculateHappiness(Client client) {
        return this.food.calculateHappiness(client);
    }

    @Override
    public String toString() {
        return this.food.toString();
    }
}
