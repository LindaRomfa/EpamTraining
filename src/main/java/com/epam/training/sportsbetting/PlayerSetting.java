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
        LocalDate birth = null;

        boolean correctInput;
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
        PlayerBuilder playerBuilder = new PlayerBuilder();
        Player player;
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
