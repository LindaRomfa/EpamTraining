package com.epam.training.restaurant;

import com.epam.training.restaurant.domain.Client;
import com.epam.training.restaurant.domain.Order;
import com.epam.training.restaurant.factory.FoodFactory;
import com.epam.training.restaurant.robot.CookRobot;

import java.util.Arrays;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {

        //just tests
        Order order1 = new Order("chips", new LinkedList<>(Arrays.asList("ketchup","mustard")));
        Order order2 = new Order("hotdog",new LinkedList<>(Arrays.asList("ketchup")));
        Client Balazs = new Client("Balazs",100);
        Client Peter = new Client("Peter",200);

        CookRobot robot = new CookRobot();
        robot.addOrder(Balazs,order1);
        robot.addOrder(Peter,order2);
        robot.processOrders();

        Peter.consume(FoodFactory.createFood(order2));
    }
}
