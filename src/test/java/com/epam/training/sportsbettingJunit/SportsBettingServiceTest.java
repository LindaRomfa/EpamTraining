package com.epam.training.sportsbettingJunit;

import com.epam.training.sportsbetting.service.GameData;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.service.SportsBettingImplement;
import com.epam.training.sportsbetting.ui.Input;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SportsBettingServiceTest {
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private static final Player TEST_PLAYER = new Player("jakapbgipsz@gmail.com", "pass1234"
            , "Jakab Gipsz", 12345678, new BigDecimal(1000)
            , LocalDate.parse("1920.12.04", LOCAL_DATE_FORMATTER), Currency.HUF);

    private static final GameData TEST_DATA = new GameData();

    private static final Player TEST_FIND_PLAYER = TEST_DATA.getPlayers().get(0);

    SportsBettingImplement underTest;

    @Mock
    Input input;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        underTest = new SportsBettingImplement(input,TEST_DATA);
    }

    @Test
    public void findPlayerTestShouldReturnPlayerInputValidNameAndPassword(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("Jakab Gipsz").willReturn("pass1234");
        //WHEN
        Player result = underTest.findPlayer();
        //THEN
        Assert.assertEquals(TEST_FIND_PLAYER,result);
    }

    @Test
    public void findPlayerTestShouldReturnPlayerWhenWrongNameAfterCorrectName(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("Valami Mas").willReturn("pass1234");
        //WHEN
        Player result = underTest.findPlayer();
        //THEN
        Assert.assertEquals(null,result);
    }
}
