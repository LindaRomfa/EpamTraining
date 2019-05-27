package com.epam.training.restaurant.domain;

import com.epam.training.restaurant.observer.Observable;
import com.epam.training.restaurant.observer.Observer;

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
        System.out.println("Client: I'm eating");
    }

    public void update(Food food){

    }

    @Override
    public String toString() {
        return "Client[" +
                "name='" + name + '\'' +
                ", happiness=" + happiness +
                ']';
    }
}
