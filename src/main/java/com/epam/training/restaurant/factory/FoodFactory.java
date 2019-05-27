package com.epam.training.restaurant.factory;

import com.epam.training.restaurant.domain.Chips;
import com.epam.training.restaurant.domain.Food;
import com.epam.training.restaurant.domain.Hotdog;
import com.epam.training.restaurant.domain.Order;

import java.util.List;

public class FoodFactory {
    private static final String HOTDOG = "hotdog";
    private static final String CHIPS = "chips";
    private static final String MUSTARD = "mustard";
    private static final String KETCHUP = "ketchup";

    public FoodFactory() {
    }

    public Food createFood(Order order) {
        return addExtras(createMainFood(order.getFood()), order.getExtras());
    }

    public Food createMainFood(String type) {
        if (type.equalsIgnoreCase(HOTDOG)) {
            return new Hotdog();
        } else if (type.equalsIgnoreCase(CHIPS)) {
            return new Chips();
        }
        return null;
    }

    public Food addExtras(Food food, List<String> extras) {
        return null;
    }

}
