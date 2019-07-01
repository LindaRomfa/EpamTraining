package com.epam.training.gameoflife;

public class Coordinate {

    private final int positionX;
    private final int positionY;

    public Coordinate(int positionX, int positionY) {
        super();
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + positionX;
        result = prime * result + positionY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (positionX != other.positionX)
            return false;
        if (positionY != other.positionY)
            return false;
        return true;
    }
}

