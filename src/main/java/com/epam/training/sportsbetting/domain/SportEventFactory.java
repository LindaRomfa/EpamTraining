package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;

public class SportEventFactory {
    public static SportEvent getSportEvent(String type,String title, LocalDateTime startDate, LocalDateTime endDate){
        if("FootballSport".equalsIgnoreCase(type)) return new FootballSportEvent(title,startDate,endDate);
        else if("TennisSport".equalsIgnoreCase(type)) return new TennisSportEvent(title,startDate,endDate);
        return null;
    }
}
