package com.epam.training.sportsbetting.builder;


import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.Result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FootballSportEventBuilder {
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<Bet> bets = new ArrayList<>();
        private Result result;

        public FootballSportEventBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public FootballSportEventBuilder setStartDate(LocalDateTime startDate){
            this.startDate = startDate;
            return this;
        }

        public FootballSportEventBuilder setEndDate(LocalDateTime endDate){
            this.endDate = endDate;
            return this;
        }

        public FootballSportEventBuilder setBets(List<Bet> bets){
            this.bets = bets;
            return this;
        }

        public FootballSportEventBuilder setResult(Result result){
            this.result = result;
            return this;
        }

        public FootballSportEvent builder(){
            return new FootballSportEvent(title,startDate,endDate,bets,result);
        }
}
