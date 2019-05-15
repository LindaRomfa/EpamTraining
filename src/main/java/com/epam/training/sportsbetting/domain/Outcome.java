package com.epam.training.sportsbetting.domain;

import java.util.List;

public class Outcome {
    private String description;
    private List<OutcomeOdd> outcomeOdds;
    private Bet bet;

    public String getDescription() {
        return description;
    }

    public Bet getBet() {
        return bet;
    }

    public Outcome(String description) {
        this.description = description;
    }

    public void creatOutcomeData(Bet bet, List<OutcomeOdd> outcomeOdds){
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;
    }

}
