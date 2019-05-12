package com.epam.training.sportsbetting.domain;

import java.util.List;

public class Outcome {
    private String description;
    private List<OutcomeOdd> outcomeOdds;
    private Bet bet;

    public String getDescription() {
        return description;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public Bet getBet() {
        return bet;
    }

    public Outcome(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
}
