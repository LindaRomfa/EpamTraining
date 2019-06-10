package com.epam.training.sportsbetting;


import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.service.SportsBettingImplement;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.ui.Input;
import com.epam.training.sportsbetting.ui.View;
import com.epam.training.sportsbetting.ui.ViewImplement;


import java.util.*;

public class App {
    private View view;
    private SportsBettingService sportsBettingService;
    private List<Wager> wagers = new ArrayList<>();
    public static TestData testDatas = new TestData();
    private Player usedPlayer;
    private Input input;

    public static void main(String[] args) {

        App appSingleton = AppSingleton.getApp();
        appSingleton.play();

    }

    public App(View view, SportsBettingService sportsBettingService, Input input) {
        this.view = view;
        this.sportsBettingService = sportsBettingService;
        this.sportsBettingService.setDatas(testDatas);
        this.input = input;
    }

    public App() {
        view = new ViewImplement();
        sportsBettingService = new SportsBettingImplement();
        sportsBettingService.setDatas(testDatas);
        input = new Input();
    }

    public void play() {
        sportsBettingService.setDatas(testDatas);
        System.out.println("Welcome!");
        usedPlayer = setUsedPlayer();

        view.printWelcomeMessage(usedPlayer);
        doBetting();
        calculateResults();
        printResults();
    }

    public Player setUsedPlayer() {
        String existsProfile;
        System.out.println("Do you have a profile? [y/n]:");
        do {
            existsProfile = input.getInput();
            if (existsProfile.equals("n")) {
                return creatPlayer();
            } else if (existsProfile.equals("y")) {
                return sportsBettingService.findPlayer();
            } else {
                System.out.println("Incorrect output, please try again! [y/n]:");
            }
        } while (true);

    }

    public void doBetting() {
        view.printOutcomeOdds(testDatas.getOdds());
        wagers = view.creatWagers(usedPlayer, testDatas.getOdds());
    }

    public Player creatPlayer() {
        Player player = view.readPlayerData();
        sportsBettingService.savePlayer(player);
        return player;
    }

    public void calculateResults() {
        sportsBettingService.calculateResults(usedPlayer, wagers);
    }

    public void printResults() {
        view.printResults(usedPlayer, wagers);
    }
}
