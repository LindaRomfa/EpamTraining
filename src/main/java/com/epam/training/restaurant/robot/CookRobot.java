package com.epam.training.restaurant.robot;

import com.epam.training.restaurant.domain.Client;
import com.epam.training.restaurant.domain.Food;
import com.epam.training.restaurant.domain.Order;
import com.epam.training.restaurant.factory.FoodFactory;

import java.util.ArrayList;
import java.util.List;

public class CookRobot {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Client client, Order order) {
        System.out.println("CookRobot: Order registered, client:" + client + ", order: " + order);
        orders.add(order);
    }

    public void processOrders() {
        System.out.format("CookRobot: Processing %d order(s)...\n", orders.size());

        for (Order order : orders) {
            Food food = FoodFactory.createFood(order);
            order.notifyObserver(food);
        }
        System.out.println("CookRobot: Order processed");
    }
}
