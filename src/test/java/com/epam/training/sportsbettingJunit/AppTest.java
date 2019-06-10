package com.epam.training.sportsbettingJunit;

import com.epam.training.sportsbetting.App;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.ui.Input;
import com.epam.training.sportsbetting.ui.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppTest {
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private static final Player TEST_PLAYER = new Player("jakab@gmail.com","pass1234",
            "Jakab Gipsz",12345678,new BigDecimal(1000),
            LocalDate.parse("1997.03.02", LOCAL_DATE_FORMATTER), Currency.HUF);

    App underTest;

    @Mock
    View view;

    @Mock
    SportsBettingService sportsBettingService;

    @Mock
    Input input;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        underTest = new App(view,sportsBettingService,input);
    }

    @Test
    public void setUsedPlayerTestShouldFindPlayer(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("y");
        BDDMockito.given(sportsBettingService.findPlayer()).willReturn(TEST_PLAYER);
        //WHEN
        Player result = underTest.setUsedPlayer();
        //THEN
        Assert.assertEquals(TEST_PLAYER,result);

    }

    @Test
    public void setUsedPlayerTestShouldCreatNewPlayer(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("n");
        BDDMockito.given(view.readPlayerData()).willReturn(TEST_PLAYER);
        //WHEN
        Player result = underTest.setUsedPlayer();
        //THEN
        Assert.assertEquals(TEST_PLAYER,result);
    }
}
