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

    public BetType getType() {
        return type;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public String getDescription() {
        return description;
    }

    public Bet(String description){
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public String toString(){
        return "Bet" + getDescription();
    }
}
