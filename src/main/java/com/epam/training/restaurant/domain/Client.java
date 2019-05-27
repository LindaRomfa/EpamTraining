package com.epam.training.restaurant.domain;

public class Client {
    private String name;
    private double happiness;

    public Client(String name, double happiness) {
        this.name = name;
        this.happiness = happiness;
    }

    public double getHappiness() {
        return happiness;
    }

    public void consume(Food food){
        this.happiness = food.calculateHappiness(this);
        System.out.println("I'm eating");
    }

}
