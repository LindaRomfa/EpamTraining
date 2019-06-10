package com.epam.training.sportsbetting.ui;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public String getInput(){
        return scanner.nextLine();
    }

}
