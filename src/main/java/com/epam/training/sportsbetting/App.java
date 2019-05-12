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
    //private List<Outcome> outcomes = new ArrayList<>();
    private List<OutcomeOdd> odds = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Player usedPlayer;

    public static void main(String[] args){
        App game = new App();
        game.creatTestData();
        game.play();
    }

    private void play() {
        System.out.println("Welcome!");
        System.out.println("Do you have a profile? [y/n]:");
        String yesOrNo;
        boolean correctInput = false;
        do {
             yesOrNo = scanner.nextLine();
            if (yesOrNo.equals("n")) {
                creatPlayer();
                correctInput = true;
            }
            else if (yesOrNo.equals("y")) {
                usedPlayer = findPlayer();
                correctInput = true;
            }
            else{
                System.out.println("Incorrect output, please try again! [y/n]:");
            }
        }while(!correctInput);
        printWelcomeMessage(usedPlayer);
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
        return null;
    }

    @Override
    public void saveWager(Wager wager) {

    }

    @Override
    public List<Wager> findAllWagers() {
        return null;
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
        return null;
    }

    @Override
    public void printWagerSaved(Wager wager) {

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
        //creatTestOutcome();
        creatTestOdd();
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

    private void creatTestOdd() {
        LocalDateTime startDate = LocalDateTime.parse("2000-01-01 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);
        odds.add(new OutcomeOdd(new BigDecimal(2),startDate,endDate));
        odds.add(new OutcomeOdd(new BigDecimal(3),startDate,endDate));
    }

   /* private void creatTestOutcome() {
        outcomes.add(new Outcome("1"));
        outcomes.add(new Outcome("3"));
        outcomes.add(new Outcome("Arsenal"));
        outcomes.add(new Outcome("Chelsea"));
    }*/

    private void creatTestBet() {
        bets.add(new Bet("Winner"));
        bets.add(new Bet("Player Oliver Giroud score"));
        bets.add(new Bet("Number of scored goals"));
        bets.get(0).setType(BetType.WINNER);
        bets.get(1).setType(BetType.PLAYERS_SCORE);
        bets.get(2).setType(BetType.GOALS);
        List<Outcome> outcomeList = new ArrayList<>();
        outcomeList.add(new Outcome("1"));
        outcomeList.get(0).setOutcomeOdds(odds);
        bets.get(0).setOutcomes(outcomeList);
        outcomeList.remove(0);
        outcomeList.add(new Outcome("3"));
        outcomeList.get(0).setOutcomeOdds(odds);
        bets.get(1).setOutcomes(outcomeList);
        outcomeList.remove(0);
        outcomeList.add(new Outcome("Arsenal"));
        outcomeList.add(new Outcome("Chelsea"));
        outcomeList.get(0).setOutcomeOdds(odds);
        outcomeList.get(1).setOutcomeOdds(odds);
        bets.get(2).setOutcomes(outcomeList);
    }

    private void creatTestSportEvent() {
        LocalDateTime startDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 16:00:00", DATE_TIME_FORMATTER);
        sportEvents.add(new FootballSportEvent("Arsenal vs Chelsea",startDate,endDate));
        sportEvents.get(0).setBets(bets);
    }
}
