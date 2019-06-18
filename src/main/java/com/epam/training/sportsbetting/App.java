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
    private Player usedPlayer;
    private Input input;

    public static void main(String[] args) {

        App appSingleton = AppSingleton.getApp();
        appSingleton.play();
    }

    public App(View view, SportsBettingService sportsBettingService, Input input) {
        this.view = view;
        this.sportsBettingService = sportsBettingService;
        this.input = input;
    }

    public App() {
        view = new ViewImplement();
        sportsBettingService = new SportsBettingImplement();
        input = new Input();
    }

    public void play() {
        System.out.println("Welcome!");
        usedPlayer = setUsedPlayer();

        view.printWelcomeMessage(usedPlayer);
        doBetting();
        calculateResults();
        printResults();
    }

    public Player setUsedPlayer() {
        String existsProfile;
        Player player = null;
        System.out.println("Do you have a profile? [y/n]:");
        do {
            existsProfile = input.getInput();
            if(!existsProfile.equals("n") && !existsProfile.equals("y")){
                System.out.println("Incorrect output, please try again! [y/n]:");
            }
            if (existsProfile.equals("n")) {
                player = creatPlayer();
            } else if (existsProfile.equals("y")) {
               player = sportsBettingService.findPlayer();
            }
            if(player == null){
                System.out.println("Are you sure you have a profile? [y/n]:");
            }

        } while (player == null);
        return player;
    }

    public void doBetting() {
        List<OutcomeOdd> odds = sportsBettingService.getOutcomeOdds();
        view.printOutcomeOdds(odds);
        wagers = view.creatWagers(usedPlayer,odds);
    }

    private Player creatPlayer() {
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
