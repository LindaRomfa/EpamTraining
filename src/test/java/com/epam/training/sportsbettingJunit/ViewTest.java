package com.epam.training.sportsbettingJunit;

import com.epam.training.sportsbetting.TestData;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.ui.Input;
import com.epam.training.sportsbetting.ui.ViewImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ViewTest {
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private static final Player TEST_PLAYER = new Player("jakab@gmail.com","pass1234",
            "Jakab Gipsz",12345678,new BigDecimal(1000),
            LocalDate.parse("1997.03.02", LOCAL_DATE_FORMATTER), Currency.HUF);
    private static final String AMOUNT_TEST = "100";
    private static final String BIRTH_TEST = "1997.03.02";
    private static final TestData TEST_DATA = new TestData();
    private static final Wager TEST_WAGER = new Wager(new BigDecimal(100), LocalDateTime.now(),false
            ,false,TEST_PLAYER.getCurrency(),TEST_PLAYER,TEST_DATA.getOdds().get(0));
    private static final Integer TEST_BALANCE = 1000;

    ViewImplement underTest;

    @Mock
    Input input;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        underTest = new ViewImplement(input);
    }

    @Test
    public void readWagerAmountTestShouldReturnBigDecimalWhenInvalidInputAfterCorrectInput(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("2000").willReturn(AMOUNT_TEST);
        //WHEN
        BigDecimal result = underTest.readWagerAmount(TEST_PLAYER);
        //THEN
        Assert.assertEquals(new BigDecimal(AMOUNT_TEST),result);
    }

    @Test
    public void readPlayerDataTestShouldReturnPlayer(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("jakab@gmail.com").willReturn("pass1234")
                .willReturn("Jakab Gipsz").willReturn("12345678").willReturn("1997.03.02")
                .willReturn("1000").willReturn("HUF");
        //WHEN
        Player result = underTest.readPlayerData();
        //THEN
        //Assert.assertTrue(TEST_PLAYER.equals(result));
    }

    @Test
    public void addBirthTestShouldReturnLocalDateWhenInvalidInputAfterCorrectInput(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("1997-02-03").willReturn(BIRTH_TEST);
        //WHEN
        LocalDate result = underTest.addBirth();
        //THEN
        Assert.assertEquals(LocalDate.parse(BIRTH_TEST, LOCAL_DATE_FORMATTER),result);
    }


    @Test
    public void addCurrencyTestShouldReturnEurCurrencyWhenInputIsEUR(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("EUR");
        //WHEN
        Currency result = underTest.addCurrency();
        //THEN
        Assert.assertEquals(Currency.EUR,result);
    }
    @Test
    public void addCurrencyTestShouldReturnUsdCurrencyWhenInputIsUSD(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("USD");
        //WHEN
        Currency result = underTest.addCurrency();
        //THEN
        Assert.assertEquals(Currency.USD,result);
    }

    @Test
    public void addCurrencyTestShouldReturnHufCurrenyWhenInvalidInputAfterCorrectInput(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("ASDAD").willReturn("HUF");
        //WHEN
        Currency result = underTest.addCurrency();
        //THEN
        Assert.assertEquals(Currency.HUF,result);
    }

    @Test
    public void addBalanceShouldReturnBalanceWhenTooSmallNumberAfterCorrectNumber(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("-100").willReturn(String.valueOf(TEST_BALANCE));
        //WHEN
        Integer result = underTest.addBalance();
        //THEN
        Assert.assertEquals(TEST_BALANCE,result);
    }

    @Test
    public void addWagerTestShouldReturnWager(){
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("100");
        //WHEN
        Wager result = underTest.addWager(TEST_PLAYER,TEST_DATA.getOdds(),1);
        TEST_WAGER.setTimestampCreated(result.getTimestampCreated());
        //THEN
        //Assert.assertTrue(TEST_WAGER.equals(result));
    }

}
