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

    public Wager(BigDecimal amount, LocalDateTime timestampCreated, boolean processed,
                 boolean win, Currency currency, Player player, OutcomeOdd odd) {
        this.amount = amount;
        this.timestampCreated = timestampCreated;
        this.processed = processed;
        this.win = win;
        this.currency = currency;
        this.player = player;
        this.odd = odd;
    }

    public Player getPlayer() {
        return player;
    }

    public Currency getCurrency() {
        return currency;
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

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    @Override
    public String toString() {
        return "Wager{" +
                "amount=" + amount +
                ", timestampCreated=" + timestampCreated +
                ", processed=" + processed +
                ", win=" + win +
                ", odd=" + odd +
                ", player=" + player +
                ", currency=" + currency +
                '}';
    }
}
