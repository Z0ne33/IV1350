package se.kth.iv1350.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.integration.InventorySystem;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Sale;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller instanceToTest;
    private int quantityTest;

    @BeforeEach
    public void setUp() {
        instanceToTest = new Controller();
        instanceToTest.startSale();
        quantityTest = 3;


    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        quantityTest = 0;
    }
    @ParameterizedTest
    @ValueSource(strings = {"apple","orange", "BigWheel Oatmeal"})
    public void testFetchItem(String testID) {
        instanceToTest.fetchItem(testID, quantityTest);
        boolean expResult = true;
        assertEquals(expResult, instanceToTest.getSale().getSaleDetails().checkItem(testID));
    }
    @Test
    public void testFetchQuantityTooHigh() {
        quantityTest = 20;
        String itemID = "apple";
        instanceToTest.fetchItem(itemID, quantityTest);
        boolean expResult = false;
        assertEquals(expResult, instanceToTest.getSale().getSaleDetails().checkItem(itemID));
    }
    @Test
    public void testFetchItemWithWrongID() {
        String itemID = "HDHH";
        instanceToTest.fetchItem(itemID, quantityTest);
        boolean expResult = false;
        assertEquals(expResult, instanceToTest.getSale().getSaleDetails().checkItem(itemID));


    }
    @Test
    public void testIfPaymentReturnsChange() {
        String itemID = "apple";
        quantityTest = 1;
        instanceToTest.fetchItem(itemID, quantityTest);
        Amount payment = new Amount(12.5, "SEK");
        Amount change = instanceToTest.payment(payment);
        Amount expResult = new Amount(0);
        assertTrue(change.equals(expResult));


    }

}
