package com.epam.training.restaurant.factory;

import com.epam.training.restaurant.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class FoodFactory {
    private static final String HOTDOG = "hotdog";
    private static final String CHIPS = "chips";
    private static final String MUSTARD = "mustard";
    private static final String KETCHUP = "ketchup";

    public FoodFactory() {
    }

    public static AbsFood createFood(Order order) {

        System.out.format("FoodFactory: Preparing food, order: %s\n", order);
//        Food newFood = addExtras(createMainFood(order.getFood()), order.getExtras());

        AbsFood mainFood = createMainFood(order.getFood());
        mainFood.setExtras(createExtras(order.getExtras()));

        System.out.println("FoodFactory: food prepared:, food: " + mainFood);
        return mainFood;
    }

    private static List<AbsExtra> createExtras(List<String> extras) {
        return extras.stream().map(FoodFactory::mapExtras).collect(Collectors.toList());
    }

    private static AbsExtra mapExtras(String extra) {
        if (extra.equalsIgnoreCase(MUSTARD)) {
            return new Mustard();
        } else {
            return new Ketchup();
        }
    }

    public static AbsFood createMainFood(String type) {
        if (type.equalsIgnoreCase(HOTDOG)) {
            return new Hotdog();
        } else {
            return new Chips();
        }
    }

//    public static Food addExtras(Food food, List<String> extras) {
//        if (extras.size() != 0) {
//            if (extras.get(extras.size() - 1).equalsIgnoreCase(MUSTARD)) {
//                extras.remove(extras.size() - 1);
//                return new Mustard(addExtras(food, extras));
//            } else if (extras.get(extras.size() - 1).equalsIgnoreCase(KETCHUP)) {
//                extras.remove(extras.size() - 1);
//                return new Ketchup(addExtras(food, extras));
//            }
//        }
//        return food;
//    }

}
