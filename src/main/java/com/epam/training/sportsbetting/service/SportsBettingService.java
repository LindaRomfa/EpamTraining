package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;

import java.util.List;

public interface SportsBettingService {
    void savePlayer(Player player);
    Player findPlayer();
    void calculateResults(Player player,List<Wager> wagers);
    List<OutcomeOdd> getOutcomeOdds();
}
