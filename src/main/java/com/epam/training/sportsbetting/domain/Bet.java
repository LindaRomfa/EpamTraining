package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Bet {
    private String description;
    private SportEvent event;
    private BetType type;
    private List<Outcome> outcomes = new ArrayList<>();

    public Bet(String description, SportEvent event, BetType type, List<Outcome> outcomes) {
        this.description = description;
        this.event = event;
        this.type = type;
        this.outcomes = outcomes;
    }

    public Bet(){}

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

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
