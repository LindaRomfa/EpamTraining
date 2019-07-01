package com.epam.training.gameoflife;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class DefaultBoardTest {

    private Coordinate COORDINATE_TEST = new Coordinate(1,1);

    private Board underTest;

    private List<Coordinate> coordinateList_test;

    @Before
    public void setUp(){
        coordinateList_test = new ArrayList<>();
        underTest = new DefaultBoard(coordinateList_test);
    }

    @Test
    public void isAliveTestShouldTrueIfListedInTheCoordinatesList(){
        //GIVEN
        coordinateList_test.add(new Coordinate(1,1));
        //WHEN
        Boolean result = underTest.isAlive(COORDINATE_TEST);
        System.out.println(result);
        //THEN
        Assert.assertTrue(result);
    }

}
