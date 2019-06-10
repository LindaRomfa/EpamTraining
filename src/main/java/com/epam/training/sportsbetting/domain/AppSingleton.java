package com.epam.training.sportsbetting.domain;


import com.epam.training.sportsbetting.App;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.ui.View;

public class AppSingleton {

    SportsBettingService sportsBettingService;
    private static App game = new App();

    private AppSingleton() {
    }

    public static App getApp() {
        return game;
    }
}
