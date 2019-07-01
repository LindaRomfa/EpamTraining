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
            this.isAlive(coordinate);
        }
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
            if (coordinates.contains(coordinate)) {
                return true;
            }

        return false;
    }
}

