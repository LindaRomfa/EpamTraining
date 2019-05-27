package com.epam.training.restaurant.robot;

import com.epam.training.restaurant.domain.Client;
import com.epam.training.restaurant.domain.Order;

import java.util.LinkedList;
import java.util.Queue;

public class CookRobot {
    private Queue<Order> orders = new LinkedList<>();

    public void addOrder(Client client,Order order){
        System.out.println("CookRobot: Order registered, client:" + client + ", order: " + order);
        orders.add(order);
    }

    public void processOrders(){
        System.out.format("CookRobot: Processing %d order(s)...\n",orders.size());
    }
}
