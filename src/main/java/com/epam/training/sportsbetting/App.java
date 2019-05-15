package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.ui.IO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private List<Result> results = new ArrayList<>();
    private Player usedPlayer;
    private int bettingNumb = 1;

    public static void main(String[] args){
        App game = new App();
        game.creatTestData();
        game.play();
    }

    private void play() {
        System.out.println("Welcome!");
        System.out.println("Do you have a profile? [y/n]:");
        String existsProfile;
        boolean correctInput = false;
        do {
            existsProfile = scanner.nextLine();
            if (existsProfile.equals("n")) {
                creatPlayer();
                correctInput = true;
            }
            else if (existsProfile.equals("y")) {
                usedPlayer = findPlayer();
                correctInput = true;
            }
            else{
                System.out.println("Incorrect output, please try again! [y/n]:");
            }
        }while(!correctInput);
        printWelcomeMessage(usedPlayer);
        doBetting();
    }

    private void doBetting() {
        listBetting();
        creatWagers();
    }

    private void creatWagers() {
        String chooseNumber;
        int intChooseNumber;
        BigDecimal amount;
        boolean inputNumber;
        do{
            inputNumber = true;
            chooseNumber = scanner.nextLine();
            if(chooseNumber.equals("q")){
                inputNumber = false;
            }else{
                intChooseNumber = Integer.parseInt(chooseNumber);
                if(intChooseNumber >= bettingNumb || intChooseNumber <= 0){
                    System.out.println("Invalid bet number, please try again: ");
                }else {
                    amount = readWagerAmount();
                    Wager wager = new Wager(amount, LocalDateTime.now(), false, false);
                    wager.setCurrency(usedPlayer.getCurrency());
                    wager.setPlayer(usedPlayer);
                    wager.setOdd(odds.get(intChooseNumber-1));
                    wagers.add(wager);
                    printWagerSaved(wager);
                    printBalance(usedPlayer);
                    listBetting();
                }
            }
        }while(inputNumber);
    }

    private void listBetting() {
        System.out.println("What are you want to bet on? (choose a number or press q for quit)");
        for( OutcomeOdd odd: odds){
            System.out.println(bettingNumb + ": Sport Event: " + odd.getOutcome().getBet().getEvent().getTitle() + "(start: " +
                    odd.getOutcome().getBet().getEvent().getStartDate() + "), Bet: " +
                    odd.getOutcome().getBet().getDescription() + ", Outcome: " +
                    odd.getOutcome().getDescription() + ", Actual Odd: " +
                    odd.getValue() + ", Valid between " + odd.getValidFrom() + " and " + odd.getValidUnit());
            bettingNumb++;
        }
    }

    private void creatPlayer() {
        usedPlayer = readPlayerData();
        savePlayer(usedPlayer);
    }

    @Override
    public void savePlayer(Player player) {
        players.add(player);
    }

    @Override
    public Player findPlayer() {
        Boolean validName = false;
        String playerName;
        String password;
        do {
            System.out.println("What is your name?");
            playerName = scanner.nextLine();
            System.out.println("Password: ");
            password = scanner.nextLine();
            for (Player player : players) {
                if (player.getName().equals(playerName) && player.getPassword().equals(password)) {
                    return player;
                }
            }
            System.out.println("Wrong name or password, please try again:");
        }while(!validName);
        return null;
    }

    @Override
    public List<SportEvent> findAllSportEvent() {
        return sportEvents;
    }

    @Override
    public void saveWager(Wager wager) {
        wagers.add(wager);
    }

    @Override
    public List<Wager> findAllWagers() {
        return wagers;
    }

    @Override
    public void calculateResults() {

    }

    @Override
    public Player readPlayerData() {
        boolean correctInput;
        Player player;
        System.out.println("Create new account!");
        System.out.println("Please, enter the datas: ");
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Account number:");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Birthday: [yyyy.MM.dd]");
        LocalDate birth = null;
        do{
            correctInput = true;
            try {
                birth = LocalDate.parse(scanner.nextLine(), LOCAL_DATE_FORMATTER);
            }catch(DateTimeParseException e){
                correctInput = false;
                System.out.println("Wrong format, please try again!");
            }
        }while(!correctInput);
        int balance;
        System.out.println("How much money do you have? (more than 0)");
        do {
            correctInput = true;
            balance = Integer.parseInt(scanner.nextLine());
            if(balance <= 0){
                correctInput = false;
                System.out.println("Too small number, please try again!");
            }
        }while (!correctInput);
        player = new Player(email, password, name, accountNumber, new BigDecimal(balance), birth);
        System.out.println("What is your currency? (HUF, EUR or USD)");
        do {
            correctInput = true;
            String currency = scanner.nextLine();
            if (currency.equals("HUF")) {
                player.setCurrency(Currency.HUF);
            } else if (currency.equals("EUR")) {
                player.setCurrency(Currency.EUR);
            } else if (currency.equals("USD")) {
                player.setCurrency(Currency.USD);
            }else{
                correctInput = false;
                System.out.println("Invalid currency, please try again!");
            }
        }while(!correctInput);
        System.out.println("Thank you! :)");
        return player;
    }

    @Override
    public void printWelcomeMessage(Player player) {
        System.out.println("Welcome " + player.getName());
        printBalance(player);
    }

    @Override
    public void printBalance(Player player) {
        System.out.println("Your balance is " + player.getBalance() + " " + player.getCurrency());
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> sportEvents) {

    }

    @Override
    public OutcomeOdd selectOutcomeOdd(List<SportEvent> sportEvents) {
        return null;
    }

    @Override
    public BigDecimal readWagerAmount() {
        BigDecimal amounts;
        boolean validBalance;
        do {
            System.out.println("What amount do you wish to bet on it?");
            validBalance = true;
            amounts = new BigDecimal(scanner.nextLine());
            if (amounts.compareTo(usedPlayer.getBalance()) > 0) {
                printNotEnoughBalance(usedPlayer);
                validBalance = false;
            }else{
                usedPlayer.setBalance(usedPlayer.getBalance().subtract(amounts));
                return amounts;
            }
        }while(!validBalance);
        return null;
    }

    @Override
    public void printWagerSaved(Wager wager) {
        System.out.println("Wager '" + wager.getOdd().getOutcome().getBet().getDescription() + "=" + wager.getOdd().getOutcome().getDescription() +
        "' of " + wager.getOdd().getOutcome().getBet().getEvent().getTitle() + " [odd: " +
                wager.getOdd().getValue() + ", amount: " + wager.getAmount() + "] saved!");

    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.println("You don't have enought money, your balance is " + player.getBalance() + " " + player.getCurrency());
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {

    }

    private void creatTestData(){
        creatTestSportEvent();
        creatTestBet();
        creatTestPlayer();
    }

    private void creatTestPlayer() {
        players.add(new Player("jakapbgipsz@gmail.com","pass1234","Jakab Gipsz",
                12345678,new BigDecimal(1000), LocalDate.parse("1920.12.04",LOCAL_DATE_FORMATTER)));
        players.get(0).setCurrency(Currency.HUF);
        players.add(new Player("farkasbalazs@gmail.com","pass5678","Farkas Balazs",
                87654321,new BigDecimal(200), LocalDate.parse("1996.10.02",LOCAL_DATE_FORMATTER)));
        players.get(1).setCurrency(Currency.EUR);
    }

    private void creatTestBet() {
        LocalDateTime startDate = LocalDateTime.parse("2000-01-01 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);
        //creat Winner bet
        Bet winnerBet = new Bet("Winner");
        Outcome arsenal = new Outcome("Arsenal");
        Outcome chelsea = new Outcome("Chelsea");
        OutcomeOdd arsenalOdd = new OutcomeOdd(new BigDecimal(2),startDate,endDate);
        OutcomeOdd chelseaOdd = new OutcomeOdd(new BigDecimal(3),startDate,endDate);
        arsenal.creatOutcomeData(winnerBet,creatOddList(arsenalOdd));
        arsenalOdd.setOutcome(arsenal);
        chelsea.creatOutcomeData(winnerBet,creatOddList(chelseaOdd));
        chelseaOdd.setOutcome(chelsea);
        winnerBet.creatBetData(BetType.WINNER,creatOutcomeList(arsenal,chelsea),sportEvents.get(0));
        //creat player bet
        Bet playerBet = new Bet("Player Oliver Giroud score");
        Outcome one = new Outcome("1");
        OutcomeOdd oneOdd = new OutcomeOdd(new BigDecimal(2),startDate,endDate);
        oneOdd.setOutcome(one);
        one.creatOutcomeData(playerBet,creatOddList(oneOdd));
        playerBet.creatBetData(BetType.PLAYERS_SCORE,creatOutcomeList(one),sportEvents.get(0));
        //creat goal bet
        Bet goalBet = new Bet("Number of scored goals");
        Outcome three = new Outcome("3");
        OutcomeOdd threeOdd = new OutcomeOdd(new BigDecimal(3),startDate,endDate);
        threeOdd.setOutcome(three);
        three.creatOutcomeData(goalBet,creatOddList(threeOdd));
        goalBet.creatBetData(BetType.GOALS,creatOutcomeList(three),sportEvents.get(0));
        // add lists
        outcomes.add(arsenal);
        outcomes.add(chelsea);
        outcomes.add(one);
        outcomes.add(three);
        bets.add(winnerBet);
        bets.add(playerBet);
        bets.add(goalBet);
        odds.add(arsenalOdd);
        odds.add(chelseaOdd);
        odds.add(oneOdd);
        odds.add(threeOdd);
    }

    private List<Outcome> creatOutcomeList(Outcome... outs){
        List<Outcome> outcomeList = new ArrayList<>();
        for(Outcome outcome : outs) {
            outcomeList.add(outcome);
        }
        return outcomeList;
    }

    private List<OutcomeOdd> creatOddList(OutcomeOdd... odds){
        List<OutcomeOdd> oddsList = new ArrayList<>();
        for(OutcomeOdd odd : odds){
            oddsList.add(odd);
        }
        return oddsList;
    }

    private void creatTestSportEvent() {
        LocalDateTime startDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 16:00:00", DATE_TIME_FORMATTER);
        SportEvent footballEvent = new FootballSportEvent("Arsenal vs Chelsea",startDate,endDate);
        footballEvent.setBets(bets);
        sportEvents.add(footballEvent);
    }
}
