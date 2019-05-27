package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.BetType;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.SportEvent;

import java.util.ArrayList;
import java.util.List;

public class BetBuilder {
    private String description;
    private SportEvent event;
    private BetType type;
    private List<Outcome> outcomes = new ArrayList<>();

    public BetBuilder() {
        new Bet();
    }

    public BetBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BetBuilder setEvent(SportEvent event) {
        this.event = event;
        return this;
    }

    public BetBuilder setType(BetType type) {
        this.type = type;
        return this;
    }

    public BetBuilder setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        return this;
    }

    public Bet builder() {
        return new Bet(description, event, type, outcomes);
    }
}
