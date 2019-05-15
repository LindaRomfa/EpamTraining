package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUnit;

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public LocalDateTime getValidUnit() {
        return validUnit;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUnit) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUnit = validUnit;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    private Outcome outcome;
}
