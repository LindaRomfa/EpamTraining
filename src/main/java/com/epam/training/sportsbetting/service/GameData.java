package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.builder.BetBuilder;
import com.epam.training.sportsbetting.builder.OutcomeBuilder;
import com.epam.training.sportsbetting.builder.OutcomeOddBuilder;
import com.epam.training.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameData {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private List<SportEvent> sportEvents = new ArrayList<>();
    private List<Bet> bets = new ArrayList<>();
    private List<Outcome> outcomes = new ArrayList<>();
    private List<OutcomeOdd> odds = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Result result = new Result();

    public GameData(){
        creatTestData();
    }

    public List<OutcomeOdd> getOdds() {
        return odds;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private void creatTestData() {
        creatTestSportEvent();
        creatTestBet();
        creatTestPlayer();
    }

    private void creatTestPlayer() {
        players.add(new Player("jakapbgipsz@gmail.com", "pass1234", "Jakab Gipsz",
                12345678, new BigDecimal(1000), LocalDate.parse("1920.12.04", LOCAL_DATE_FORMATTER), Currency.HUF));
        players.add(new Player("farkasbalazs@gmail.com", "pass5678", "Farkas Balazs",
                87654321, new BigDecimal(200), LocalDate.parse("1996.10.02", LOCAL_DATE_FORMATTER), Currency.EUR));
    }

    private void creatTestBet() {

        LocalDateTime startDate = LocalDateTime.parse("2000-01-01 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);

        //creat Winner bet
        Bet winnerBet = new BetBuilder().setDescription("Winner")
                .setEvent(sportEvents.get(0)).setType(BetType.WINNER).builder();

        Outcome arsenal = new OutcomeBuilder().setDescriptipn("Arsenal").setBet(winnerBet).builder();
        OutcomeOdd arsenalOdd = new OutcomeOddBuilder().setValue(new BigDecimal(2))
                .setValidFrom(startDate).setValidUnit(endDate).setOutcome(arsenal).builder();
        arsenal.setOutcomeOdds(Arrays.asList(arsenalOdd));

        Outcome chelsea = new OutcomeBuilder().setDescriptipn("Chelsea").setBet(winnerBet).builder();
        OutcomeOdd chelseaOdd = new OutcomeOddBuilder().setValue(new BigDecimal(3))
                .setValidFrom(startDate).setValidUnit(endDate).setOutcome(chelsea).builder();
        chelsea.setOutcomeOdds(Arrays.asList(chelseaOdd));

        winnerBet.setOutcomes(Arrays.asList(arsenal, chelsea));

        //creat player bet
        Bet playerBet = new BetBuilder().setDescription("Player Oliver Giroud score")
                .setEvent(sportEvents.get(0)).setType(BetType.PLAYERS_SCORE).builder();

        Outcome one = new OutcomeBuilder().setDescriptipn("1").setBet(playerBet).builder();
        OutcomeOdd oneOdd = new OutcomeOddBuilder().setValue(new BigDecimal(2))
                .setValidFrom(startDate).setValidUnit(endDate).setOutcome(one).builder();
        one.setOutcomeOdds(Arrays.asList(oneOdd));

        playerBet.setOutcomes(Arrays.asList(one));

        //creat goal bet
        Bet goalBet = new BetBuilder().setDescription("Number of scored goals")
                .setEvent(sportEvents.get(0)).setType(BetType.GOALS).builder();

        Outcome three = new OutcomeBuilder().setDescriptipn("3").setBet(goalBet).builder();
        OutcomeOdd threeOdd = new OutcomeOddBuilder().setValue(new BigDecimal(3))
                .setValidFrom(startDate).setValidUnit(endDate).setOutcome(three).builder();
        three.creatOutcomeData(goalBet, Arrays.asList(threeOdd));

        goalBet.setOutcomes(Arrays.asList(three));

        // add lists
        outcomes.addAll(Arrays.asList(arsenal, chelsea, one, three));
        bets.addAll(Arrays.asList(winnerBet, playerBet, goalBet));
        odds.addAll(Arrays.asList(arsenalOdd, chelseaOdd, oneOdd, threeOdd));
    }

    private void creatTestSportEvent() {

        LocalDateTime startDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 16:00:00", DATE_TIME_FORMATTER);

        SportEvent footballEvent = SportEventFactory.getSportEvent("FootballSport", "Arsenal vs Chelsea", startDate, endDate);
        footballEvent.setBets(bets);
        footballEvent.setResult(result);
        sportEvents.add(footballEvent);
    }
}
