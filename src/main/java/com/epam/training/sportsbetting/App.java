package com.epam.training.sportsbetting;


import com.epam.training.sportsbetting.builder.BetBuilder;
import com.epam.training.sportsbetting.builder.OutcomeBuilder;
import com.epam.training.sportsbetting.builder.OutcomeOddBuilder;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.ui.IO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App implements IO, SportsBettingService {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static Scanner scanner = new Scanner(System.in);
    private List<SportEvent> sportEvents = new ArrayList<>();
    private List<Bet> bets = new ArrayList<>();
    private List<Outcome> outcomes = new ArrayList<>();
    private List<OutcomeOdd> odds = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Wager> wagers = new ArrayList<>();
    private Result result = new Result();
    private Player usedPlayer;
    private int bettingNumber;

    public static void main(String[] args) {

        App appSingleton = AppSingleton.getApp();
        appSingleton.creatTestData();
        appSingleton.play();
    }

    private void play() {
        System.out.println("Welcome!");
        System.out.println("Do you have a profile? [y/n]:");
        String existsProfile;

        //We need this cycle, because it checks the inputs(y or n)
        boolean correctInput = false;
        do {
            existsProfile = scanner.nextLine();
            if (existsProfile.equals("n")) {
                creatPlayer();
                correctInput = true;
            } else if (existsProfile.equals("y")) {
                usedPlayer = PlayerSetting.findPlayer(players);
                correctInput = true;
            } else {
                System.out.println("Incorrect output, please try again! [y/n]:");
            }
        } while (!correctInput);

        Prints.printWelcomeMessage(usedPlayer);
        doBetting();
        calculateResults();
        Prints.printResults(wagers, usedPlayer);
    }

    private void doBetting() {
        bettingNumber = Prints.listBetting(odds);
        creatWagers();
    }

    //Player setting methods
    private void creatPlayer() {
        usedPlayer = PlayerSetting.readPlayerData();
        savePlayer(usedPlayer);
    }

    @Override
    public void savePlayer(Player player) {
        players.add(player);
    }


    //Wagers methods
    private void creatWagers() {
        String chooseNumber;
        int intChooseNumber;
        BigDecimal amount;
        boolean inputNumber;

        do {
            inputNumber = true;
            chooseNumber = scanner.nextLine();
            if (chooseNumber.equals("q")) {
                inputNumber = false;
            } else {
                intChooseNumber = Integer.parseInt(chooseNumber);
                if (intChooseNumber >= bettingNumber || intChooseNumber <= 0) {
                    System.out.println("Invalid bet number, please try again: ");
                } else {
                    amount = readWagerAmount();
                    Wager wager = new Wager(amount, LocalDateTime.now(), false, false);
                    wager.creatWagerData(usedPlayer.getCurrency(), usedPlayer, odds.get(intChooseNumber - 1));
                    wagers.add(wager);
                    Prints.printWagerSaved(wager);
                    Prints.printBalance(usedPlayer);
                    bettingNumber = Prints.listBetting(odds);
                }
            }
        } while (inputNumber);
    }

    @Override
    public BigDecimal readWagerAmount() {
        BigDecimal amounts;
        do {
            System.out.println("What amount do you wish to bet on it?");
            amounts = new BigDecimal(scanner.nextLine());
            if (amounts.compareTo(usedPlayer.getBalance()) > 0) {
                Prints.printNotEnoughBalance(usedPlayer);
            } else {
                usedPlayer.setBalance(usedPlayer.getBalance().subtract(amounts));
                return amounts;
            }
        } while (true);
    }

    @Override
    public void calculateResults() {
        int randomNumber;
        Random rand = new Random();
        List<Outcome> winnerOutcomes = new ArrayList<>();
        for (Wager wager : wagers) {
            if (!wager.isProcessed()) {
                randomNumber = rand.nextInt(2);
                if (randomNumber == 1) {
                    wager.setWin(true);
                    winnerOutcomes.add(wager.getOdd().getOutcome());
                    usedPlayer.setBalance(usedPlayer.getBalance()
                            .add(wager.getAmount().multiply(wager.getOdd().getValue())));
                }
                wager.setProcessed(true);
            }
        }
        result.setWinnerOutcome(winnerOutcomes);
    }

    //Create test methods
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
