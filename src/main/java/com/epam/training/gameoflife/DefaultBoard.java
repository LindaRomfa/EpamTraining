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
        return null;
    }

    @Override
    public void insertCell(Coordinate coordinate) {
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
        return false;
    }
}

