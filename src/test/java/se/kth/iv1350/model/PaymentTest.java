package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Payment instanceToTest;
    private Amount testPayment;
    private Amount testTotalPrice;
    private Currency testCurrency;

    @BeforeEach
    public void setUp() {
        testTotalPrice = new Amount(50, "SEK");
        testPayment = new Amount(100,"SEK" );
        instanceToTest = new Payment(testPayment, testTotalPrice);
        testCurrency = Currency.getInstance("SEK");

    }
    @AfterEach
    public void tearDown() {
        testPayment = null;
        instanceToTest = null;
        testTotalPrice = null;
    }
    @Test
    public void receiveChangeTest() {
        Amount expResultNum = testPayment.minus(testTotalPrice);
        boolean expResult = true;
        assertEquals(expResult,expResultNum.equals(instanceToTest.getChange()));
    }
    @Test
    public void testIfChangeHasCorrectCurrency() {
        String expResult = testCurrency.getCurrencyCode();
        String actualResults = instanceToTest.getChange().getCurrency().getCurrencyCode();
        assertEquals(expResult, actualResults);
    }
    @Test
    public void testIfPaymentLessThanTotalCost() {
        testTotalPrice = new Amount(100);
        testPayment = new Amount(50);
        instanceToTest = new Payment(testPayment, testTotalPrice);
        assertTrue(instanceToTest.getChange().getAmount() < 0);
    }



}
