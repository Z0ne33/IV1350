package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmountTest {

    private Amount instanceToTest;
    private double amountTest;
    private String currencyTest;

    @BeforeEach
    public void setUp() {
        amountTest = 30;
        currencyTest = "SEK";
        instanceToTest = new Amount(amountTest, currencyTest);

    }
    @AfterEach
    public void tearDown() {
        amountTest = 0;
        currencyTest = null;
        instanceToTest = null;

    }

    @Test
    public void additionTest() {
        Amount test = new Amount(30);
        Amount expResults = new Amount(60);
        Amount results = instanceToTest.addition(test);
        assertTrue(expResults.equals(results));
    }
    @Test
    public void minusTest() {
        Amount test = new Amount(30);
        Amount expResults = new Amount(0);
        Amount results = instanceToTest.minus(test);
        assertTrue(expResults.equals(results));

    }
    @ParameterizedTest
    @ValueSource(strings = {"USD","SEK","AUD","EUR"})
    public void testIfCurrencyCorrectObj(String currencyTest){
        instanceToTest = new Amount(amountTest, currencyTest);
        assertTrue(instanceToTest.getCurrency().getCurrencyCode().equals(currencyTest) && instanceToTest.getCurrency() instanceof Currency  );

    }
    @Test
    public void testIfWrongCurrencyCode(){
        String wrongCurrencyTest = "HDHA";
        boolean result = true;
        boolean expResults = false;
        try {
            instanceToTest = new Amount(100, wrongCurrencyTest);
            result = true;

        }
        catch (Exception e){
            result = false;
        }

        assertEquals(expResults,result);

    }
    @Test
    public void equalsTest(){
        Amount test1 = new Amount(10);
        Amount test2 = new Amount(30);
        assertTrue(!instanceToTest.equals(test1) &&  instanceToTest.equals(test2));


    }
}
