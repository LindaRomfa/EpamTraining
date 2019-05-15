package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Bet {
    private String description;
    private SportEvent event;
    private BetType type;
    private List<Outcome> outcomes = new ArrayList<>();

    public SportEvent getEvent() {
        return event;
    }

    public String getDescription() {
        return description;
    }

    public Bet(String description){
        this.description = description;
    }

    public String toString(){
        return "Bet" + getDescription();
    }

    public void creatBetData(BetType type,List<Outcome> outcomes, SportEvent event){
        this.type = type;
        this.outcomes = outcomes;
        this.event = event;
    }
}
