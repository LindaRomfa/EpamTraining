package com.epam.training.sportsbetting;


import com.epam.training.sportsbetting.builder.PlayerBuilder;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.ui.IO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static void main(String[] args){
        App game = new App();
        game.creatTestData();
        game.play();
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
            }
            else if (existsProfile.equals("y")) {
                usedPlayer = findPlayer();
                correctInput = true;
            }
            else{
                System.out.println("Incorrect output, please try again! [y/n]:");
            }
        }while(!correctInput);
        Prints.printWelcomeMessage(usedPlayer);
        doBetting();
        calculateResults();
        Prints.printResults(wagers,usedPlayer);
    }

    private void doBetting() {
        bettingNumber = Prints.listBetting(odds);
        creatWagers();
    }

    //Player setting methods
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
        }while(true);
    }

    @Override
    public Player readPlayerData() {
        boolean correctInput;
        PlayerBuilder playerBuilder = new PlayerBuilder();
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

        //builder test
        player = playerBuilder.setName(name).setAccountNumber(accountNumber).setBalance(new BigDecimal(balance))
                .setEmail(email).setPassword(password).setBirth(birth).getPlayer();

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

    //Wagers methods
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
                if(intChooseNumber >= bettingNumber || intChooseNumber <= 0){
                    System.out.println("Invalid bet number, please try again: ");
                }else {
                    amount = readWagerAmount();
                    Wager wager = new Wager(amount, LocalDateTime.now(), false, false);
                    wager.creatWagerData(usedPlayer.getCurrency(),usedPlayer,odds.get(intChooseNumber-1));
                    wagers.add(wager);
                    Prints.printWagerSaved(wager);
                    Prints.printBalance(usedPlayer);
                    bettingNumber = Prints.listBetting(odds);
                }
            }
        }while(inputNumber);
    }

    @Override
    public BigDecimal readWagerAmount() {
        BigDecimal amounts;
        do {
            System.out.println("What amount do you wish to bet on it?");
            amounts = new BigDecimal(scanner.nextLine());
            if (amounts.compareTo(usedPlayer.getBalance()) > 0) {
                Prints.printNotEnoughBalance(usedPlayer);
            }else{
                usedPlayer.setBalance(usedPlayer.getBalance().subtract(amounts));
                return amounts;
            }
        }while(true);
    }

    @Override
    public void calculateResults() {
        int randomNumber;
        Random rand = new Random();
        List<Outcome> winnerOutcomes = new ArrayList<>();
        for(Wager wager : wagers){
            if(wager.isProcessed() == false){
                randomNumber = rand.nextInt(2);
                if(randomNumber == 1){
                    wager.setWin(true);
                    winnerOutcomes.add(wager.getOdd().getOutcome());
                    usedPlayer.setBalance(usedPlayer.getBalance().add(wager.getAmount().multiply(wager.getOdd().getValue())));
                }
                wager.setProcessed(true);
            }
        }
        result.setWinnerOutcome(winnerOutcomes);
    }

    //Create test methods
    private void creatTestData(){
        creatTestSportEvent();
        creatTestBet();
        creatTestPlayer();
    }

    private void creatTestPlayer() {
        players.add(new Player("jakapbgipsz@gmail.com","pass1234","Jakab Gipsz",
                12345678,new BigDecimal(1000), LocalDate.parse("1920.12.04",LOCAL_DATE_FORMATTER),Currency.HUF));
        players.add(new Player("farkasbalazs@gmail.com","pass5678","Farkas Balazs",
                87654321,new BigDecimal(200), LocalDate.parse("1996.10.02",LOCAL_DATE_FORMATTER),Currency.EUR));
    }

    private void creatTestBet() {
        LocalDateTime startDate = LocalDateTime.parse("2000-01-01 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);

        //creat Winner bet
        Bet winnerBet = new Bet("Winner");

        Outcome arsenal = new Outcome("Arsenal");
        OutcomeOdd arsenalOdd = new OutcomeOdd(new BigDecimal(2),startDate,endDate);
        arsenal.creatOutcomeData(winnerBet,Arrays.asList(arsenalOdd));
        arsenalOdd.setOutcome(arsenal);

        Outcome chelsea = new Outcome("Chelsea");
        OutcomeOdd chelseaOdd = new OutcomeOdd(new BigDecimal(3),startDate,endDate);
        chelsea.creatOutcomeData(winnerBet,Arrays.asList(chelseaOdd));
        chelseaOdd.setOutcome(chelsea);

        winnerBet.creatBetData(BetType.WINNER,Arrays.asList(arsenal,chelsea),sportEvents.get(0));

        //creat player bet
        Bet playerBet = new Bet("Player Oliver Giroud score");

        Outcome one = new Outcome("1");
        OutcomeOdd oneOdd = new OutcomeOdd(new BigDecimal(2),startDate,endDate,one);
        one.creatOutcomeData(playerBet,Arrays.asList(oneOdd));
        playerBet.creatBetData(BetType.PLAYERS_SCORE,Arrays.asList(one),sportEvents.get(0));

        //creat goal bet
        Bet goalBet = new Bet("Number of scored goals");

        Outcome three = new Outcome("3");
        OutcomeOdd threeOdd = new OutcomeOdd(new BigDecimal(3),startDate,endDate,three);
        three.creatOutcomeData(goalBet,Arrays.asList(threeOdd));

        goalBet.creatBetData(BetType.GOALS,Arrays.asList(three),sportEvents.get(0));

        // add lists
        outcomes.addAll(Arrays.asList(arsenal,chelsea,one,three));
        bets.addAll(Arrays.asList(winnerBet,playerBet,goalBet));
        odds.addAll(Arrays.asList(arsenalOdd,chelseaOdd,oneOdd,threeOdd));
    }

    private void creatTestSportEvent() {
        LocalDateTime startDate = LocalDateTime.parse("2020-12-12 12:00:00", DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-12 16:00:00", DATE_TIME_FORMATTER);
        SportEvent footballEvent = new FootballSportEvent("Arsenal vs Chelsea",startDate,endDate);
        footballEvent.setBets(bets);
        footballEvent.setResult(result);
        sportEvents.add(footballEvent);
    }
}
