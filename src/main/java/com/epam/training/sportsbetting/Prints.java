package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.domain.*;

import java.util.List;

public class Prints {

    public static void printNotEnoughBalance(Player player) {
        System.out.println("You don't have enought money, your balance is " + player.getBalance() + " " + player.getCurrency());
    }

    public static void printWagerSaved(Wager wager) {
        Outcome wagerOutcome = wager.getOdd().getOutcome();
        Bet wagerBet = wagerOutcome.getBet();
        System.out.format("Wager '%s=%s' of %s [odd: %s, amount: %s] saved!\n",wagerBet.getDescription(),wagerOutcome.getDescription(),
                wagerBet.getEvent().getTitle(),wagerBet.getEvent().getTitle(),wager.getOdd().getValue(),wager.getAmount());
    }

    public static int listBetting(List<OutcomeOdd> odds) {
        int bettingNumber = 0;
        Bet oddBet;
        SportEvent oddEvent;

        System.out.println("What are you want to bet on? (choose a number or press q for quit)");
        for( OutcomeOdd odd: odds){
            oddBet = odd.getOutcome().getBet();
            oddEvent = oddBet.getEvent();
            bettingNumber++;
            System.out.format("%d: Sport event: %s(start: %s), Bet: %s, Outcome: %s, Actual Odd: %s, Valid between %s and %s\n",bettingNumber,
                    oddEvent.getTitle(),oddEvent.getStartDate(),oddBet.getDescription(),odd.getOutcome().getDescription(),odd.getValue(),odd.getValidFrom(),odd.getValidUnit());
        }
        return bettingNumber;
    }

    public static void printResults(List<Wager> wagers,Player usedPlayer) {
        System.out.println("Results:");
        Outcome wagerOutcome;
        Bet wagerBet;
        for(Wager wager : wagers){
            wagerOutcome = wager.getOdd().getOutcome();
            wagerBet = wagerOutcome.getBet();
            System.out.format("Wager '%s = %s' of %s [odd: %s, amount: %s ], Win: %s\n",wagerBet.getDescription(),
                    wagerOutcome.getDescription(), wagerBet.getEvent().getTitle(),wager.getOdd().getValue(),wager.getAmount(), wager.isWin());
        }
        System.out.println("Your new balance is " + usedPlayer.getBalance() + " " + usedPlayer.getCurrency());
    }

    public static void printWelcomeMessage(Player player) {
        System.out.println("Welcome " + player.getName());
        printBalance(player);
    }

    public static void printBalance(Player player) {
        System.out.println("Your balance is " + player.getBalance() + " " + player.getCurrency());
    }
}
