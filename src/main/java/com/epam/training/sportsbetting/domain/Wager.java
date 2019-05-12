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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setOdd(OutcomeOdd odd) {
        this.odd = odd;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
