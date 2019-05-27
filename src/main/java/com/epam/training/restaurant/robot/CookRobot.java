package com.epam.training.restaurant.robot;

import com.epam.training.restaurant.domain.Client;
import com.epam.training.restaurant.domain.Order;

import java.util.Queue;

public class CookRobot {
    private Queue<Order> orders;

    public void addOrder(Client client,Order order){
        orders.add(order);
    }

    public void processOrders(){
        orders.remove();
    }
}
