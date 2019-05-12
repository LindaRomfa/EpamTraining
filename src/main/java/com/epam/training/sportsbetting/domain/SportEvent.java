package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class SportEvent {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Bet> bets = new ArrayList<>();
    private Result result;


    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String toString(){
        return "Sport event:" + getTitle() + " (start: " + getStartDate() + ")";
    }
}
