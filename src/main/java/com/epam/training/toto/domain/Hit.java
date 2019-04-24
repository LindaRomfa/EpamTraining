package com.epam.training.toto.domain;

public class Hit {

    private int hitCount;
    private int numberOfWagers;
    private int prize;

    public int getHitCount() {
        return hitCount;
    }
    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
    public int getNumberOfWagers() {
        return numberOfWagers;
    }
    public void setNumberOfWagers(int numberOfWagers) {
        this.numberOfWagers = numberOfWagers;
    }
    public int getPrize() {
        return prize;
    }
    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void Hit(int a, int b, int c) {

    }

    public String toString() {
        return " hit count:" + getHitCount() + " number of wagers:" + getNumberOfWagers() + " prize:" + getPrize() + " Ft \n";
    }
}
