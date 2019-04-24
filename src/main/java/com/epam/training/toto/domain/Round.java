package com.epam.training.toto.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private int year;
    private int week;
    private int roundOfWeek;
    private LocalDate date;
    private List<Hit> hits = new ArrayList<>();
    private List<Outcome> outcomes = new ArrayList<>();

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getRoundOfWeek() {
        return roundOfWeek;
    }

    public void setRoundOfWeek(int roundOfWeek) {
        this.roundOfWeek = roundOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Hit> getHits() {
        //return new ArrayList<Hit>(this.hits);
        return hits;
    }

    public void setHits(List<Hit> hit){
        hits.addAll(hit);
    }

    public List<Outcome> getOutcomes(){
        return outcomes;
    }
    public void setOutcomes(List<Outcome> outcomeList) {
        outcomes.addAll(outcomeList);
    }

    public String toString() {
        return "year:" + getYear() + " week:" + getWeek() + " round of Week:" + getRoundOfWeek() + " date: " + getDate() + hits + "\n" + "  outcome:"
                + outcomes + "\n";
    }




}