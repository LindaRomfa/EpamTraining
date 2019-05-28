package com.epam.training.restaurant.domain;

public interface Food {
    double getEffect();
    void setEffect(double effect);
    double calculateHappiness(Client client);

}
