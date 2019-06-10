package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.TestData;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.ui.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportsBettingImplement implements SportsBettingService {
    Input input;
    TestData testDatas;

    public SportsBettingImplement(Input input){
        this.input = input;
    }

    public SportsBettingImplement(){
        input = new Input();
    }

    @Override
    public void setDatas(TestData datas){
        this.testDatas = datas;
    }
    @Override
    public void savePlayer(Player player) {
        testDatas.getPlayers().add(player);
    }

    @Override
    public Player findPlayer() {
        do {
            String playerName = addName();

            String password = addPassword();

            for (Player player : testDatas.getPlayers()) {
                if (player.getName().equals(playerName) && player.getPassword().equals(password)) {
                    return player;
                }
            }
            System.out.println("Wrong name or password, please try again:");
        } while (true);
    }

    public String addName() {
        System.out.println("What is your name?");
        return input.getInput();
    }

    public String addPassword() {

        System.out.println("Password: ");
        return input.getInput();
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return null;
    }


    @Override
    public void saveWager(Wager wager) {

    }

    @Override
    public List<Wager> findAllWagers() {
        return null;
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
