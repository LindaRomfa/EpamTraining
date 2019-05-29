package com.epam.training.restaurant.observer;

import com.epam.training.restaurant.domain.AbsFood;
import com.epam.training.restaurant.domain.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private List<Observer> observers = new ArrayList<>();

    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    public void notifyObserver(AbsFood food) {
        System.out.println("Order: Notifying observers of " + food);
        for (Observer observer : observers) {
            observer.update(food);
            System.out.println("Order: Notification done.");
        }
    }


}
