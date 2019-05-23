package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
    private BigDecimal amount;
    private LocalDateTime timestampCreated;
    private boolean processed;
    private boolean win;
    private OutcomeOdd odd;
    private Player player;
    private Currency currency;

    public Wager(BigDecimal amount, LocalDateTime timestampCreated, boolean processed, boolean win) {
        this.amount = amount;
        this.timestampCreated = timestampCreated;
        this.processed = processed;
        this.win = win;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isProcessed() {
        return processed;
    }

    public boolean isWin() {
        return win;
    }

    public OutcomeOdd getOdd() {
        return odd;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void creatWagerData(Currency currency,Player player,OutcomeOdd odd){
        this.currency = currency;
        this.player = player;
        this.odd = odd;
    }
}
