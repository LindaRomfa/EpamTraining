package com.epam.training.sportsbetting.ui;


import com.epam.training.sportsbetting.builder.PlayerBuilder;
import com.epam.training.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewImplement implements View {
    Input input;

    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public ViewImplement(Input input){
        this.input = input;
    }

    public ViewImplement(){
        input = new Input();
    }

    @Override
    public Player readPlayerData() {
        System.out.println("Create new account!");
        System.out.println("Please, enter the datas: ");

        System.out.println("Email:");
        String email = input.getInput();

        System.out.println("Password:");
        String password = input.getInput();

        System.out.println("Name: ");
        String name = input.getInput();

        System.out.println("Account number:");
        int accountNumber;
        do{
            accountNumber = addAccountNumber(input.getInput());
        }while(accountNumber == 0);


        System.out.println("Birthday: [yyyy.MM.dd]");
        LocalDate localDateBirth;
        do{
            localDateBirth = addBirth(input.getInput());
        }while(localDateBirth == null);

        System.out.println("How much money do you have? (more than 0)");
        int balance;
        do{
            balance = addBalance(input.getInput());
        }while (balance == 0);

        System.out.println("What is your currency? (HUF, EUR or USD)");
        Currency currency;
        do{
            currency = addCurrency(input.getInput());
        }while (currency == null);

        System.out.println("Thank you! :)");

        //builder test
        return new PlayerBuilder().setName(name).setAccountNumber(accountNumber).setBalance(new BigDecimal(balance))
                .setEmail(email).setPassword(password).setBirth(localDateBirth).setCurrency(currency).builder();
    }

    private int addAccountNumber(String input) {
        int accountNumber;
        try {
            accountNumber = Integer.parseInt(input);
        }catch (Exception e){
            System.out.println("This is not a number, please try again!");
            accountNumber = 0;
        }
        return accountNumber;
    }

    private int addBalance(String input) {
        int balance = Integer.parseInt(input);
        if(balance <= 0){
            System.out.println("Too small number, please try again!");
            balance = 0;
        }
        return balance;
    }

    private LocalDate addBirth(String input){
        LocalDate date = null;
        try{
            date = LocalDate.parse(input, LOCAL_DATE_FORMATTER);
        }catch (Exception e){
            System.out.println("Wrong format, please try again!");
        }
        return date;
    }

    private Currency addCurrency(String input) {

            String currency = input;
            if (currency.equals("HUF")) {
                return Currency.HUF;
            } else if (currency.equals("EUR")) {
                return Currency.EUR;
            } else if (currency.equals("USD")) {
                return Currency.USD;
            } else {
                System.out.println("Invalid currency, please try again!");
                return null;
            }
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
    public void printOutcomeOdds(List<OutcomeOdd> odds) {
        Bet oddBet;
        SportEvent oddEvent;
        System.out.println("What are you want to bet on? (choose a number or press q for quit)");
        for (OutcomeOdd odd : odds) {
            oddBet = odd.getOutcome().getBet();
            oddEvent = oddBet.getEvent();
            System.out.format("%d: Sport event: %s(start: %s), Bet: %s, Outcome: %s," +
                            " Actual Odd: %s, Valid between %s and %s\n", odd.getId(),
                    oddEvent.getTitle(), oddEvent.getStartDate(), oddBet.getDescription(),
                    odd.getOutcome().getDescription(), odd.getValue(), odd.getValidFrom(), odd.getValidUnit());
        }
    }

    @Override
    public List<Wager> creatWagers(Player player, List<OutcomeOdd> odds) {
        List<Wager> wagers = new ArrayList<>();
        String chooseNumber;
        int intChooseNumber;

        do {
            chooseNumber = input.getInput();
            if (chooseNumber.equals("q")) {
                return wagers;
            } else {
                intChooseNumber = Integer.parseInt(chooseNumber);
                if (intChooseNumber >= OutcomeOdd.getStaticId() || intChooseNumber <= 0) {
                    System.out.println("Invalid bet number, please try again: ");
                } else {
                    Wager wager = addWager(player,odds,intChooseNumber);
                    wagers.add(wager);
                }
            }
        } while (true);
    }

    private Wager addWager(Player player, List<OutcomeOdd> odds,int ChooseNumber){
        BigDecimal amount = readWagerAmount(player);
        Wager wager = new Wager(amount, LocalDateTime.now(), false, false,
                player.getCurrency(), player, odds.get(ChooseNumber - 1));
        printWagerSaved(wager);
        printBalance(player);
        printOutcomeOdds(odds);
        return wager;
    }

    @Override
    public BigDecimal readWagerAmount(Player player) {
        BigDecimal amounts;
        do {
            System.out.println("What amount do you wish to bet on it?");
            amounts = new BigDecimal(input.getInput());
            if (amounts.compareTo(player.getBalance()) > 0) {
                printNotEnoughBalance(player);
            } else {
                player.setBalance(player.getBalance().subtract(amounts));
                return amounts;
            }
        } while (true);
    }

    @Override
    public void printWagerSaved(Wager wager) {
        Outcome wagerOutcome = wager.getOdd().getOutcome();
        Bet wagerBet = wagerOutcome.getBet();
        System.out.format("Wager '%s=%s' of %s [odd: %s, amount: %s] saved!\n",
                wagerBet.getDescription(), wagerOutcome.getDescription(),
                wagerBet.getEvent().getTitle(), wagerBet.getEvent().getTitle(),
                wager.getOdd().getValue(), wager.getAmount());
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.println("You don't have enought money, your balance is " +
                player.getBalance() + " " + player.getCurrency());
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {
        System.out.println("Results:");
        Outcome wagerOutcome;
        Bet wagerBet;
        for (Wager wager : wagers) {
            wagerOutcome = wager.getOdd().getOutcome();
            wagerBet = wagerOutcome.getBet();
            System.out.format("Wager '%s = %s' of %s [odd: %s, amount: %s ], Win: %s\n", wagerBet.getDescription(),
                    wagerOutcome.getDescription(), wagerBet.getEvent().getTitle(),
                    wager.getOdd().getValue(), wager.getAmount(), wager.isWin());
        }
        System.out.println("Your new balance is " + player.getBalance() + " " + player.getCurrency());
    }
}
