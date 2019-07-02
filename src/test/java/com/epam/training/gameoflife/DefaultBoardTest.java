package com.epam.training.gameoflife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DefaultBoardTest {

    private Coordinate COORDINATE_TEST = new Coordinate(1, 1);

    private Board underTest;

    private List<Coordinate> coordinateList_test;

    private boolean insert_test = true;

    @Before
    public void setUp() {
        coordinateList_test = new ArrayList<>();
        underTest = new DefaultBoard(coordinateList_test, insert_test);
    }

    @Test
    public void isAliveTestShouldTrueIfListedInTheCoordinatesList() {
        //GIVEN
        coordinateList_test.add(new Coordinate(1, 1));
        //WHEN
        Boolean result = underTest.isAlive(COORDINATE_TEST);
        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void isAliveTestShouldReturnTrueWhenHaveTwoOrThreeNeighbor() {
        //GIVEN
        coordinateList_test.add(new Coordinate(1, 1));
        coordinateList_test.add(new Coordinate(0, 1));
        coordinateList_test.add(new Coordinate(1, 0));
        //WHEN
        Boolean result = underTest.isAlive(COORDINATE_TEST);
        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void isAliveTestShouldReturnFalseWhenNotEnoughNeighbor() {
        //GIVEN
        coordinateList_test.add(new Coordinate(1, 1));
        coordinateList_test.add(new Coordinate(0, 1));
        //WHEN
        Board resultTest = underTest.getNextGenerationBoard();
        Boolean result = resultTest.isAlive(COORDINATE_TEST);
        //THEN
        Assert.assertFalse(result);

    }

    @Test
    public void isAliveTestShouldReturnFalseWhenTooMuchNeighbor() {
        //GIVEN
        coordinateList_test.add(new Coordinate(1, 1));
        coordinateList_test.add(new Coordinate(0, 0));
        coordinateList_test.add(new Coordinate(1, 0));
        coordinateList_test.add(new Coordinate(2, 0));
        coordinateList_test.add(new Coordinate(0, 1));
        //WHEN
        Board resultTest = underTest.getNextGenerationBoard();
        Boolean result = resultTest.isAlive(COORDINATE_TEST);
        //THEN
        Assert.assertFalse(result);

    }

    @Test
    public void isAliveShouldReturnTrueWhenNotIncludedTheListButHaveThreeNeighbor() {
        //GIVEN
        coordinateList_test.add(new Coordinate(0, 0));
        coordinateList_test.add(new Coordinate(0, 1));
        coordinateList_test.add(new Coordinate(1, 0));
        //WHEN
        Board resultTest = underTest.getNextGenerationBoard();
        Boolean result = resultTest.isAlive(COORDINATE_TEST);
        //THEN
        Assert.assertTrue(result);
    }


}
