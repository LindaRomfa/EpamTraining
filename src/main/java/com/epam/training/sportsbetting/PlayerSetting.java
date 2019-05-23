package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.builder.PlayerBuilder;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PlayerSetting {
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public static Player readPlayerData() {
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
        LocalDate birth = addBirth();

        System.out.println("How much money do you have? (more than 0)");
        int balance = addBalance();

        System.out.println("What is your currency? (HUF, EUR or USD)");
        Currency currency = addCurrency();

        System.out.println("Thank you! :)");

        //builder test
        return new PlayerBuilder().setName(name).setAccountNumber(accountNumber).setBalance(new BigDecimal(balance))
                .setEmail(email).setPassword(password).setBirth(birth).setCurrency(currency).getPlayer();
    }

    private static Currency addCurrency(){
        do {
            String currency = scanner.nextLine();
            if (currency.equals("HUF")) {
                return Currency.HUF;
            } else if (currency.equals("EUR")) {
                return Currency.EUR;
            } else if (currency.equals("USD")) {
                return Currency.USD;
            }else{
                System.out.println("Invalid currency, please try again!");
            }
        }while(true);
    }

    private static int addBalance() {
        do {
            int balance = Integer.parseInt(scanner.nextLine());
            if(balance <= 0){
                System.out.println("Too small number, please try again!");
            }else{
                return balance;
            }
        }while (true);
    }

    private static LocalDate addBirth(){
        do{
            try {
                return LocalDate.parse(scanner.nextLine(), LOCAL_DATE_FORMATTER);
            }catch(DateTimeParseException e){
                System.out.println("Wrong format, please try again!");
            }
        }while(true);
    }

    public static Player findPlayer(List<Player> players) {
        do {
            System.out.println("What is your name?");
            String playerName = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            for (Player player : players) {
                if (player.getName().equals(playerName) && player.getPassword().equals(password)) {
                    return player;
                }
            }
            System.out.println("Wrong name or password, please try again:");
        }while(true);
    }
}
