package com.epam.training.restaurant.domain;

import com.epam.training.restaurant.observer.Observer;

public class Client implements Observer {
    private String name;
    private double happiness;

    public Client(String name, double happiness) {
        this.name = name;
        this.happiness = happiness;
    }

    public double getHappiness() {
        return happiness;
    }

    public void consume(AbsFood food) {
        this.happiness = food.calculateHappiness(this.happiness);
        System.out.println("Client: Csam csam nyam nyam");
    }


    @Override
    public String toString() {
        return "Client[" +
                "name='" + name + '\'' +
                ", happiness=" + happiness +
                ']';
    }

    @Override
    public void update(AbsFood food) {
        System.out.format("Client: Starting to eat food, client: %s, food: %s\n", this, food);
        this.consume(food);
        System.out.println("Client: Food eaten, client: " + this);

    }
}
