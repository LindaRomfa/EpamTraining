package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOddBuilder {
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUnit;
    private Outcome outcome;

    public OutcomeOddBuilder setValue(BigDecimal value){
        this.value = value;
        return this;
    }

    public OutcomeOddBuilder setValidFrom(LocalDateTime validFrom){
        this.validFrom = validFrom;
        return this;
    }

    public OutcomeOddBuilder setValidUnit(LocalDateTime validUnit){
        this.validUnit = validUnit;
        return this;
    }

    public OutcomeOddBuilder setOutcome(Outcome outcome){
        this.outcome = outcome;
        return this;
    }

    public OutcomeOdd builder(){
        return new OutcomeOdd(value,validFrom,validUnit,outcome);
    }

}
