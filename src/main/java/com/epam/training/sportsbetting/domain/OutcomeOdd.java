package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUnit;
    private Outcome outcome;
    private static int staticId = 1;
    private int id;

    public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUnit, Outcome outcome) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUnit = validUnit;
        this.outcome = outcome;
        id = staticId;
        staticId++;
    }

    public static int getStaticId() {
        return staticId;
    }

    public int getId() {
        return id;
    }

    public OutcomeOdd() {
    }

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

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }
}
