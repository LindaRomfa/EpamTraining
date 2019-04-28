package com.epam.training.toto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static LocalDate in;
    private static int counter = 0;
    private static List<String> outcomeList = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.d.");

    public static void main(String[] args) throws IOException {
        List<Round> rounds = readRound();
        maxPrize(rounds);
        statistics(rounds);
        dateIn();
        OutcomeListIn();
        hitCounter(rounds);
    }
    private static List<Round> readRound() throws IOException {
        List<Round> rounds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:/Epam/Training/src/main/resources/toto.csv"))) {
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                String[] line = nextLine.split(";");
                Round round = creatRound(line);
                rounds.add(round);
            }
        }
        return rounds;
    }
    private static Round creatRound(String[] nextLine) {
        Round round = new Round();
        round.setYear(Integer.parseInt(nextLine[0]));
        round.setWeek(Integer.parseInt(nextLine[1]));
        if ("-".contentEquals(nextLine[2])) {
            nextLine[2] = "1";
        }
        round.setRoundOfWeek(Integer.parseInt(nextLine[2]));
        if (!("".contentEquals(nextLine[3]))) {
            round.setDate(LocalDate.parse(nextLine[3], FORMATTER));
        } else {
            round.setDate(LocalDate.parse("2015-01-01"));
        }
        // Created the hits
        round.setHits(creatHitList(nextLine));
        // created the outcomes
        round.setOutcomes(creatOutcomeList(nextLine));
        return round;
    }
    private static List<Hit> creatHitList(String[] nextLine){
        List<Hit> hits = new ArrayList<>();
        Hit hit;
        int hitCountNumber = 14;
        for(int i = 4; i < 13;i += 2){
            hit =  creatHit(nextLine[i], nextLine[i+1], hitCountNumber);
            hits.add(hit);
            hitCountNumber--;
        }
        return hits;
    }
    private static Hit creatHit(String numberOfWager, String prize, int hitCount) {
        Hit hit = new Hit();
        prize = prize.replace(" ","").replace("Ft","");
        hit.setHitCount(hitCount);
        hit.setNumberOfWagers(Integer.parseInt(numberOfWager));
        hit.setPrize(Integer.parseInt(prize));
        return hit;
    }
    private static List<Outcome> creatOutcomeList(String[] nextLine){
        List<Outcome> outcomes = new ArrayList<>();
        for (int i = 14; i < 28; i++) {
            outcomes.add(creatOutcome(nextLine[i]));
        }
        return outcomes;
    }
    private static Outcome creatOutcome(String line) {
        Outcome outcome;
        if (line.equals("1") || line.equals("+1")) {
            outcome = Outcome._1;
        } else if (line.equals("2") || line.equals("+2")) {
            outcome = Outcome._2;
        } else {
            outcome = Outcome.x;
        }
        return outcome;
    }

    private static void dateIn() {
        System.out.println("Scanner:");
        System.out.print("Enter date: ");
        boolean incorrectDateInput ;
        do {
            incorrectDateInput = false;
            try {
                in = LocalDate.parse(sc.nextLine(), FORMATTER);
            } catch (DateTimeParseException e) {
                incorrectDateInput = true;
                System.out.println("Incorrect date, please try again: ");
            }
        }while(incorrectDateInput);

    }

    private static void OutcomeListIn() {
        System.out.print("Enter outcomes: ");
        System.out.println("");
        String outcomesIn;
        boolean incorrectOutcomeInput;
        do{
            incorrectOutcomeInput = false;
            outcomesIn = sc.nextLine();
            outcomeList = Arrays.asList(outcomesIn.split(""));
        if (outcomeList.size() != 14) {
            incorrectOutcomeInput = true;
        }

        for (String outcomes : outcomeList) {
            if (!(outcomes.equals("1")) && !(outcomes.equals("2")) && !(outcomes.equals("x"))) {
                incorrectOutcomeInput = true;
            }
        }
        if(incorrectOutcomeInput){
            System.out.println("Incorrect outcome, please try again: ");
        }
        }while(incorrectOutcomeInput);
        sc.close();
    }
    private static void hitCounter(List<Round> rounds) {
        int i = 0;
        List<Outcome> outcomes = rounds.stream().filter(x -> x.getDate().equals(in)).map(Round::getOutcomes)
                .flatMap(z -> z.stream()).collect(Collectors.toList());
        for (Outcome outcome : outcomes) {
            if ((outcomeList.get(i).equals(outcome.getValue()))) {
                counter++;
            }
            i++;
        }
        List<Hit> hits = rounds.stream().filter(x -> x.getDate().equals(in)).map(Round::getHits)
                .flatMap(z -> z.stream()).collect(Collectors.toList());
        for(Hit hit : hits) {
            if(hit.getHitCount() == counter) {
                System.out.println("Result: hits: " + hit.getHitCount() + ", amount: " + hit.getPrize() + " Ft");
            }
        }
    }
    private static void maxPrize(List<Round> rounds){
        System.out.print("Max prize ever: ");
        int MaxPrize =
                rounds.stream().map(Round::getHits).flatMap(x -> x.stream()).mapToInt(Hit::getPrize).max().getAsInt();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### Ft");
        System.out.println(decimalFormat.format(MaxPrize));
        System.out.print ("Statistic: ");
    }
    private static void statistics(List<Round> rounds){
        Supplier<Stream> outcomesCollect =() ->  rounds.stream().map(Round::getOutcomes).flatMap(l -> l.stream());
        double allCount = outcomesCollect.get().count();
        double firstCount = outcomesCollect.get().filter(i -> i == Outcome._1)
                .count();
        System.out.print("team #1 won: " + outcomePercentage(firstCount,allCount) + "%,");
        double secondCount =outcomesCollect.get().filter(i -> i == Outcome._2)
                .count();
        System.out.print(" team #2 won: " + outcomePercentage(secondCount,allCount) + "%,");
        double drawCount = outcomesCollect.get().filter(i -> i == Outcome.x)
                .count();
        System.out.println(" draw: " + outcomePercentage(drawCount,allCount) + "%");
    }
    private static String outcomePercentage(double part,double all){
        DecimalFormat countFormat = new DecimalFormat("##.##");
        return countFormat.format(part / all * 100 );
    }

}
