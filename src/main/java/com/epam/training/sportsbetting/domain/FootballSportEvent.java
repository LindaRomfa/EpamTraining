package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;

public class FootballSportEvent extends SportEvent {
    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate){
        super(title, startDate, endDate);
    }
    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets, Result result){
        super(title,startDate,endDate,bets,result);
    }
}
