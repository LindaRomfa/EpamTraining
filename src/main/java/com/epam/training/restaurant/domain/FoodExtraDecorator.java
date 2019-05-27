package com.epam.training.restaurant.domain;

public abstract class FoodExtraDecorator implements Food{

    private Food food;

    public FoodExtraDecorator(Food food){
        this.food = food;
    }
}
