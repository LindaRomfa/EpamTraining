package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.ui.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportsBettingImplement implements SportsBettingService {
    Input input;
    private GameData gameData;

    public SportsBettingImplement(Input input,GameData gameData){

        this.input = input;
        this.gameData = gameData;
    }

    public SportsBettingImplement(){
        input = new Input();
        gameData = new GameData();
    }

    @Override
    public List<OutcomeOdd> getOutcomeOdds() {
        return gameData.getOdds();
    }

    @Override
    public void savePlayer(Player player) {
        gameData.getPlayers().add(player);
    }

    @Override
    public Player findPlayer() {
            String playerName = addName();

            String password = addPassword();

            for (Player player : gameData.getPlayers()) {
                if (player.getName().equals(playerName) && player.getPassword().equals(password)) {
                    return player;
                }
            }
            System.out.println("Wrong name or password, please try again:");
            return null;
    }

    private String addName() {
        System.out.println("What is your name?");
        return input.getInput();
    }

    private String addPassword() {

        System.out.println("Password: ");
        return input.getInput();
    }

    @Override
    public void calculateResults(Player player,List<Wager> wagers) {
        int randomNumber;
        Random rand = new Random();
        List<Outcome> winnerOutcomes = new ArrayList<>();
        for (Wager wager : wagers) {
            if (!wager.isProcessed()) {
                randomNumber = rand.nextInt(2);
                if (randomNumber == 1) {
                    wager.setWin(true);
                    winnerOutcomes.add(wager.getOdd().getOutcome());
                    player.setBalance(player.getBalance()
                            .add(wager.getAmount().multiply(wager.getOdd().getValue())));
                }
                wager.setProcessed(true);
            }
        }
        //result.setWinnerOutcome(winnerOutcomes);
    }
}
