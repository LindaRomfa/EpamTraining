package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUnit;

    public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUnit) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUnit = validUnit;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidUnit(LocalDateTime validUnit) {
        this.validUnit = validUnit;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    private Outcome outcome;
}
