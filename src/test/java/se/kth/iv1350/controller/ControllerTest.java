package se.kth.iv1350.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.integration.InvalidItemException;
import se.kth.iv1350.model.Amount;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller instanceToTest;
    private int quantityTest;

    @BeforeEach
    public void setUp() throws IOException {
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
    public void testFetchItem(String testID){

        try {
            instanceToTest.fetchItem(testID, quantityTest);
            instanceToTest.setRunningTotal();
            assertEquals(true, instanceToTest.checkCart(testID));

        }
        catch (OperationFailedException | InvalidItemException e){
            fail("Could not fetch item");
        }




    }

    @Test
    public void testFetchItemWithWrongID() {
        String itemID = "HDHH";
        try {
            instanceToTest.fetchItem(itemID, quantityTest);
            fail("Could fetch item with wrong ID");
        }
        catch (InvalidItemException e){
            assertTrue(e.getMessage().contains("ERROR"));
        }
        catch (OperationFailedException e){
            fail("Database was offline");
        }





    }
    @Test
    public void testIfPaymentReturnsChange(){
        String itemID = "apple";
        quantityTest = 1;
        Amount change = new Amount(0);
        Amount expResult = new Amount(0);
        try {
            instanceToTest.fetchItem(itemID, quantityTest);
            instanceToTest.setRunningTotal();
            Amount payment = new Amount(12.5, "SEK");
            change = instanceToTest.payment(payment);
            assertTrue(change.equals(expResult));
        }
        catch (OperationFailedException | InvalidItemException e){
            fail("Could not fetch item");
        }




    }


}
