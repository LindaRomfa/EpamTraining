package com.epam.training.sportsbetting.domain;

import java.util.List;

public class Result {
    private List<Outcome> winnerOutcome;

    public void setWinnerOutcome(List<Outcome> winnerOutcome) {
        this.winnerOutcome = winnerOutcome;
    }
}
