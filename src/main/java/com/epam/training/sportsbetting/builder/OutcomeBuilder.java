package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

import java.util.List;

public class OutcomeBuilder {
        private String description;
        private List<OutcomeOdd> outcomeOdds;
        private Bet bet;

        public OutcomeBuilder setDescriptipn(String description){
            this.description = description;
            return this;
        }

        public OutcomeBuilder setOutcomeOdd(List<OutcomeOdd> outcomeOdds){
            this.outcomeOdds = outcomeOdds;
            return this;
        }

        public OutcomeBuilder setBet(Bet bet){
            this.bet = bet;
            return this;
        }

        public Outcome builder(){
            return new Outcome(description,outcomeOdds,bet);
        }
}
