package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.service.GameData;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ViewTest {
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private static final Player TEST_PLAYER = new Player("jakab@gmail.com", "pass1234",
            "Jakab Gipsz", 12345678, new BigDecimal(1000),
            LocalDate.parse("1997.03.02", LOCAL_DATE_FORMATTER), Currency.HUF);
    private static final String AMOUNT_TEST = "100";
    private static final GameData TEST_DATA = new GameData();
    private static final Wager TEST_WAGER = new Wager(new BigDecimal(100), LocalDateTime.now(), false
            , false, TEST_PLAYER.getCurrency(), TEST_PLAYER, TEST_DATA.getOdds().get(0));

    ViewImplement underTest;

    @Mock
    Input input;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        underTest = new ViewImplement(input);
    }

    @Test
    public void readWagerAmountTestShouldReturnBigDecimalWhenInvalidInputAfterCorrectInput() {
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("2000").willReturn(AMOUNT_TEST);
        //WHEN
        BigDecimal result = underTest.readWagerAmount(TEST_PLAYER);
        //THEN
        Assert.assertEquals(new BigDecimal(AMOUNT_TEST), result);
    }

    @Test
    public void readPlayerDataTestShouldReturnPlayer() {
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("jakab@gmail.com").willReturn("pass1234")
                .willReturn("Jakab Gipsz").willReturn("12345678").willReturn("1997.03.02")
                .willReturn("1000").willReturn("HUF");
        //WHEN
        Player result = underTest.readPlayerData();
        //THEN
        Assert.assertEquals(TEST_PLAYER.getEmail(), result.getEmail());
        Assert.assertEquals(TEST_PLAYER.getPassword(), result.getPassword());
        Assert.assertEquals(TEST_PLAYER.getName(), result.getName());
        Assert.assertEquals(TEST_PLAYER.getAccountNumber(), result.getAccountNumber());
        Assert.assertEquals(TEST_PLAYER.getBirth(), result.getBirth());
        Assert.assertEquals(TEST_PLAYER.getBalance(), result.getBalance());
        Assert.assertEquals(TEST_PLAYER.getCurrency(), result.getCurrency());
    }


    @Test
    public void addBalanceTestShouldReturnZeroWhenTheInputIsWrong() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //GIVEN
        Method method = ViewImplement.class.getDeclaredMethod("addBalance", String.class);
        method.setAccessible(true);
        //WHEN
        int result = (int) method.invoke(underTest, "-120");
        //THEN
        Assert.assertEquals(0, result);
    }

    @Test
    public void addAccountNumberTestShouldReturnZeroWhenTheInputIsNotNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //GIVEN
        Method method = ViewImplement.class.getDeclaredMethod("addAccountNumber", String.class);
        method.setAccessible(true);
        //WHEN
        int result = (int) method.invoke(underTest, "abcd");
        //THEN
        Assert.assertEquals(0, result);
    }

    @Test
    public void addCurrencyTestShouldReturnNullWhenTheInputIsWrong() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //GIVEN
        Method method = ViewImplement.class.getDeclaredMethod("addCurrency", String.class);
        method.setAccessible(true);
        //WHEN
        Currency result = (Currency) method.invoke(underTest, "lira");
        //THEN
        Assert.assertEquals(null, result);
    }

    @Test
    public void addBirthTestShouldReturnNullWhenTheInputIsWrongDate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //GIVEN
        Method method = ViewImplement.class.getDeclaredMethod("addBirth",String.class);
        method.setAccessible(true);
        //WHEN
        LocalDate result = (LocalDate) method.invoke(underTest,"2000-02-06");
        //THEN
        Assert.assertEquals(null,result);
    }

    @Test
    public void creatWagersTest() {
        //GIVEN
        BDDMockito.given(input.getInput()).willReturn("1").willReturn("100").willReturn("q");
        //WHEN
        List<Wager> results = underTest.creatWagers(TEST_PLAYER, TEST_DATA.getOdds());
        //
        Assert.assertEquals(TEST_WAGER.getAmount(), results.get(0).getAmount());
        Assert.assertEquals(TEST_WAGER.isProcessed(), results.get(0).isProcessed());
        Assert.assertEquals(TEST_WAGER.isWin(), results.get(0).isWin());
        Assert.assertEquals(TEST_WAGER.getOdd(), results.get(0).getOdd());
        Assert.assertEquals(TEST_WAGER.getPlayer(), results.get(0).getPlayer());
        Assert.assertEquals(TEST_WAGER.getCurrency(), results.get(0).getCurrency());
    }


}
