package com.epam.training.restaurant;

import com.epam.training.restaurant.domain.Client;
import com.epam.training.restaurant.domain.Order;
import com.epam.training.restaurant.robot.CookRobot;

import java.util.Arrays;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {

        //just tests
        Client client1 = new Client("Balazs", 100);
        Order order1 = new Order("chips", new LinkedList<>(Arrays.asList("ketchup", "mustard")), client1);

        Client client2 = new Client("Peter", 200);
        Order order2 = new Order("hotdog", new LinkedList<>(Arrays.asList("ketchup")), client2);

        CookRobot robot = new CookRobot();
        robot.addOrder(client1, order1);
        robot.addOrder(client2, order2);
        robot.processOrders();
    }
}
