package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.TennisSportEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TennisSportEventBuilder {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Bet> bets = new ArrayList<>();
    private Result result;

    public TennisSportEventBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public TennisSportEventBuilder setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
        return this;
    }

    public TennisSportEventBuilder setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
        return this;
    }

    public TennisSportEventBuilder setBets(List<Bet> bets){
        this.bets = bets;
        return this;
    }

    public TennisSportEventBuilder setResult(Result result){
        this.result = result;
        return this;
    }

    public TennisSportEvent builder(){
        return new TennisSportEvent(title,startDate,endDate,bets,result);
    }
}
