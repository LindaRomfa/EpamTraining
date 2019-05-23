package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.util.List;

public interface SportsBettingService {
    void savePlayer(Player player);
    Player findPlayer();
    void calculateResults();
}
