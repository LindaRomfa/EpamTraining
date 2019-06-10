package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.TestData;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.util.List;

public interface SportsBettingService {
    void savePlayer(Player player);
    Player findPlayer();
    List<SportEvent> findAllSportEvents();
    void saveWager(Wager wager);
    List<Wager> findAllWagers();
    void calculateResults(Player player,List<Wager> wagers);
    void setDatas(TestData datas);
}
