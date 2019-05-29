package com.epam.training.restaurant.domain;

import java.util.List;

public abstract class AbsFood {

    private List<AbsExtra> extras;

    public AbsFood() {
    }

    protected AbsFood(List<AbsExtra> extras) {
        this.extras = extras;
    }

    public List<AbsExtra> getExtras() {
        return extras;
    }

    public void setExtras(List<AbsExtra> extras) {
        this.extras = extras;
    }

    public double calculateHappiness(double happiness) {
        boolean incByExtra = extras.size() > 0;
        for (AbsExtra extra : extras) {

            happiness = extra.applyEffect(this, happiness);

            if (extra instanceof Ketchup) {
                happiness = incBaseFood(happiness, 2);
            } else {
                // mustard
                happiness += 1;
            }
        }

        if (!incByExtra) {
            happiness = incBaseFood(happiness, 1);
        }

        return happiness;
    }

    private double incBaseFood(double happiness, int extra) {
        if (this instanceof Hotdog) {
            happiness += (2 * extra);
        } else {
            happiness += happiness * (0.05 * extra);
        }
        return happiness;
    }
}
