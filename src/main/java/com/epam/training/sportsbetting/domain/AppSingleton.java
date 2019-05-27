package com.epam.training.sportsbetting.domain;


import com.epam.training.sportsbetting.App;

public class AppSingleton {

    private static App game = new App();

    private AppSingleton() {
    }

    public static App getApp() {
        return game;
    }
}
