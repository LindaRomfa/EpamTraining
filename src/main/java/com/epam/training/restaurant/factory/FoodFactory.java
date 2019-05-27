package com.epam.training.restaurant.factory;

import com.epam.training.restaurant.domain.*;

import java.util.List;

public class FoodFactory {
    private static final String HOTDOG = "hotdog";
    private static final String CHIPS = "chips";
    private static final String MUSTARD = "mustard";
    private static final String KETCHUP = "ketchup";

    public FoodFactory() {
    }

    public static Food createFood(Order order) {

        System.out.format("FoodFactory: Preparing food, order: %s\n", order);
        Food newFood = addExtras(createMainFood(order.getFood()), order.getExtras());
        System.out.println("FoodFactory: food prepared:, food: " + newFood);
        return newFood;
    }

    public static Food createMainFood(String type) {

        if (type.equalsIgnoreCase(HOTDOG)) {
            return new Hotdog();
        } else if (type.equalsIgnoreCase(CHIPS)) {
            return new Chips();
        }
        return null;
    }

    public static Food addExtras(Food food, List<String> extras) {
        if (extras.size() != 0) {
            if (extras.get(extras.size() - 1).equalsIgnoreCase(MUSTARD)) {
                extras.remove(extras.size() - 1);
                return new Mustard(addExtras(food, extras));
            } else if (extras.get(extras.size() - 1).equalsIgnoreCase(KETCHUP)) {
                extras.remove(extras.size() - 1);
                return new Ketchup(addExtras(food, extras));
            }
        }
        return food;
    }

}
