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

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static LocalDate in;
    private static String StrIn;
    private static int counter = 0;
    public static List<String> OutcomeList = new ArrayList<>();
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.d.");
    public static boolean Di, Oli;

    public static void main(String[] args) throws IOException {
        List<Round> rounds = readRound();

        System.out.print("Max prize ever: ");
        int MaxPrize =
                rounds.stream().map(Round::getHits).flatMap(x -> x.stream()).mapToInt(Hit::getPrize).max().getAsInt();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### Ft");
        System.out.println(decimalFormat.format(MaxPrize));
        System.out.print ("Statistic: ");


        DecimalFormat countFormat = new DecimalFormat("##.##");
        double allCount = rounds.stream().map(Round::getOutcomes).flatMap(l -> l.stream()).count();
        double FirstCount = rounds.stream().map(Round::getOutcomes).flatMap(l -> l.stream()).filter(i -> i == Outcome._1)
                .count();
        System.out.print("1team won: " + countFormat.format(FirstCount / allCount * 100 ) + "%,");
        double SecondCount = rounds.stream().map(Round::getOutcomes).flatMap(l -> l.stream()).filter(i -> i == Outcome._2)
                .count();
        System.out.print(" 2team won: " + countFormat.format(SecondCount / allCount * 100 ) + "%,");
        double DrawCount = rounds.stream().map(Round::getOutcomes).flatMap(l -> l.stream()).filter(i -> i == Outcome.x)
                .count();
        System.out.println(" draw: " + countFormat.format(DrawCount / allCount * 100 ) + "%");

        /*
         * System.out.println("Hit prise sum: ");
         * System.out.println(rounds.parallelStream().map(Round::getHits).flatMap(x->x.
         * stream()).mapToInt(Hit::getPrize).sum());
         *
         * long prize = 0; for (Round round : rounds) { List<Hit> hits =
         * round.getHits(); for (Hit hit : hits) { prize += hit.getPrize(); } }
         *
         * System.out.println(prize);
         */
        Di = DateIn();
        Oli = OutcomeListIn();
        sc.close();
        HitCounter(rounds, Di, Oli);


        // System.out.println(rounds);

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

        List<Hit> hits = new ArrayList<>();
        List<Outcome> outcomes = new ArrayList<>();
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
        // Hit
        Hit hit = creatHit(nextLine[4], nextLine[5], 14);
        hits.add(hit);
        hit = creatHit(nextLine[6], nextLine[7], 13);
        hits.add(hit);
        hit = creatHit(nextLine[8], nextLine[9], 12);
        hits.add(hit);
        hit = creatHit(nextLine[10], nextLine[11], 11);
        hits.add(hit);
        hit = creatHit(nextLine[12], nextLine[13], 10);
        hits.add(hit);
        round.setHits(hits);

        // Outcomes
        for (int i = 14; i < 28; i++) {
            outcomes.add(creatOutcome(nextLine[i]));

        }
        round.setOutcomes(outcomes);

        return round;
    }

    private static Hit creatHit(String line1, String line2, int numb) {
        Hit hit = new Hit();
        String line = line2.substring(0, line2.length() - 3);
        String[] arr = line.split(" ");
        StringBuilder br = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            br.append(arr[i]);
        }
        line = br.toString();
        hit.setHitCount(numb);
        hit.setNumberOfWagers(Integer.parseInt(line1));
        hit.setPrize(Integer.parseInt(line));
        return hit;
    }

    private static Outcome creatOutcome(String line) {
        Outcome outcome = null;
        if (line.equals("1") || line.equals("+1")) {
            outcome = Outcome._1;
        } else if (line.equals("2") || line.equals("+2")) {
            outcome = Outcome._2;
        } else {
            outcome = Outcome.x;
        }
        return outcome;
    }

    private static boolean DateIn() {
        System.out.println("Scanner:");
        System.out.print("Enter date: ");
        try {
            in = LocalDate.parse(sc.nextLine(),FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println(e);
            return false;
        } catch(Exception e){
            System.err.println(e);
        }
        return true;
    }

    private static boolean OutcomeListIn() {
        System.out.print("Enter outcomes: ");
        System.out.println("");
        StrIn = sc.nextLine();
        OutcomeList = Arrays.asList(StrIn.split(""));
        if (OutcomeList.size() != 14) {
            System.out.println("Incorrect input!");
            return false;
        }
        for (String o : OutcomeList) {
            if (!(o.equals("1")) && !(o.equals("2")) && !(o.equals("x"))) {
                System.out.println("Incorrect input!");
                return false;
            }
        }
        return true;
    }

    private static void HitCounter(List<Round> rounds,boolean Di, boolean Oli) {
        int i = 0;
        if (Di && Oli) {
            for (Round round : rounds) {
                if (round.getDate().equals(in)) {
                    List<Outcome> outcomes = round.getOutcomes();
                    for (Outcome outcome : outcomes) {
                        if ((OutcomeList.get(i).equals("1") && outcome.equals(Outcome._1))) {

                            counter++;
                        }
                        if(OutcomeList.get(i).equals("2") && outcome.equals(Outcome._2)) {

                            counter++;
                        }
                        if(OutcomeList.get(i++).equals("x") && outcome.equals(Outcome.x)) {

                            counter++;
                        }
                    }
                    List<Hit> hits = round.getHits();
                    for(Hit hit : hits) {
                        if(hit.getHitCount() == counter) {
                            System.out.println("Result: hits: " + hit.getHitCount() + ", amount: " + hit.getPrize() + " Ft");
                        }
                    }
                }
            }
        }else {
            System.out.println("Incorrect input!");
        }
    }


}
