package se.kth.iv1350.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.startup.Main;

public class SaleTest {

    private Sale instanceToTest;
    private StoreItem testItem;
    private Amount testPayment;
    private int testQuantity;

    @BeforeEach
    public void setUp() {
        instanceToTest = new Sale();
        testItem = new StoreItem(new ItemDTO("test", new Amount(32), "testing for sale"), "itemTest", testQuantity, 0.25);
        testQuantity = 3;
        testPayment = new Amount(10, "SEK");
    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        testItem = null;
        testPayment = null;
    }
    @Test
    public void testIfItemIsAddedToCart() {
        instanceToTest.addItem(testItem, testQuantity);
        boolean expResult = true;
        assertEquals(expResult, instanceToTest.getSaleDetails().checkItem(testItem.getItemID()));
    }
    @Test
    public void testIfIncreaseQuantity() {
        instanceToTest.addItem(testItem, testQuantity);
        double expResult = testItem.getQuantity() + testQuantity;
        instanceToTest.addItem(testItem, testQuantity);
        assertEquals(expResult, testItem.getQuantity());
    }
    @Test
    public void hasCorrectTotalCostTest(){
        instanceToTest.addItem(testItem, 1);
        instanceToTest.addPayment(testPayment);
        double testVAT = 1 + testItem.getVatRate();
        int quantity = testItem.getQuantity();
        double price = testItem.getItemDetails().getPrice().getAmount();
        double expResult = (price * testVAT) * quantity;
        assertEquals(expResult, instanceToTest.getSaleDetails().getTotalPrice().getAmount());

    }
}
