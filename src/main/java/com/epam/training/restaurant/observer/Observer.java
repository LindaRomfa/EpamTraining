package com.epam.training.restaurant.observer;

import com.epam.training.restaurant.domain.Food;

public interface Observer {
     void update(Food food);
}
