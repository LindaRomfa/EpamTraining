package com.epam.training.restaurant.observer;

import com.epam.training.restaurant.domain.AbsFood;

public interface Observer {
     void update(AbsFood food);
}
