package com.epam.training.gameoflife;


import java.util.ArrayList;
import java.util.List;

public class DefaultBoard implements Board {

    public List<Coordinate> coordinates = new ArrayList<>();

    public DefaultBoard() {

    }

    public DefaultBoard(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Board getNextGenerationBoard() {
        return new DefaultBoard(coordinates);
    }

    @Override
    public void insertCell(Coordinate coordinate) {
        if (!coordinates.contains(coordinate)) {
            coordinates.add(coordinate);
        }
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
        int neighbor = neighborCounter(coordinate);
        if (coordinates.contains(coordinate)) {
            if (neighbor == 2 || neighbor == 3) {
                return true;
            }
            if(neighbor < 2){
                return false;
            }
            return true;
        }


        return false;
    }

    private int neighborCounter(Coordinate coordinate) {
        int counter = 0;
        for (Coordinate coordinate1 : coordinates) {
            if ((coordinate1.getPositionX() == ((coordinate.getPositionX()) - 1))) {
                counter = counter + positionYCounter(coordinate1, coordinate);
            }
            if (coordinate1.getPositionX() == (coordinate.getPositionX())) {

                counter = counter + positionYMinusOne(coordinate1, coordinate);

                counter = counter + positionYPlusOne(coordinate1, coordinate);

            }
            if ((coordinate1.getPositionX() == (coordinate.getPositionX() + 1))) {
                counter = counter + positionYCounter(coordinate1, coordinate);
            }

        }
        return counter;
    }

    private int positionYCounter(Coordinate coordinate1, Coordinate coordinate) {
        int counter = 0;
        counter = counter + positionYMinusOne(coordinate1, coordinate);
        if (coordinate1.getPositionY() == (coordinate.getPositionY())) {
            counter++;
        }
        counter = counter + positionYPlusOne(coordinate1, coordinate);
        return counter;
    }

    private int positionYMinusOne(Coordinate coordinate1, Coordinate coordinate) {
        int counter = 0;
        if ((coordinate1.getPositionY() == (coordinate.getPositionY() - 1))) {
            counter++;
        }
        return counter;
    }

    private int positionYPlusOne(Coordinate coordinate1, Coordinate coordinate) {
        int counter = 0;
        if ((coordinate1.getPositionY() == (coordinate.getPositionY() + 1))) {
            counter++;
        }
        return counter;
    }
}

