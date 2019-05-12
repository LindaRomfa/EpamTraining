package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.math.BigDecimal;
import java.util.List;

public interface IO {
    Player readPlayerData();
    void printWelcomeMessage(Player player);
    void printBalance(Player player);
    void printOutcomeOdds(List<SportEvent> sportEvents);
    OutcomeOdd selectOutcomeOdd(List<SportEvent> sportEvents);
    BigDecimal readWagerAmount();
    void printWagerSaved(Wager wager);
    void printNotEnoughBalance(Player player);
    void printResults(Player player, List<Wager> wagers);
}
