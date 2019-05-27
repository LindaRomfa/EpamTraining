package com.epam.training.restaurant.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private List<Observer> observers = new ArrayList<>();

    public boolean addObserver(Observer observer){
        return observers.add(observer);
    }

    public void notifyObserver(Observer observer){

    }

    public void update(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }
}
