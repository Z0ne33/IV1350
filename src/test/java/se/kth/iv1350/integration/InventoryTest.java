package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.model.DTO.SaleDTO;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.StoreItem;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private InventorySystem instanceToTest;
    private StoreItem testItem1;
    private StoreItem testItem2;
    private Sale boughtItemsTest;

    @BeforeEach
    public void setUp() {
        instanceToTest = InventorySystem.getInstance();
        instanceToTest.setInventoryStatus("online");
        instanceToTest.createItem();
        testItem1 = new StoreItem(new ItemDTO("test1", new Amount(1), "test1"),"apple",  2, 0.25);
        testItem2 = new StoreItem(new ItemDTO("test2", new Amount(1), "test2"), "orange", 2, 0.25);
        boughtItemsTest = new Sale();
        boughtItemsTest.addToCart(testItem1);
        boughtItemsTest.addToCart(testItem2);
        boughtItemsTest.setTotal();

    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        testItem1 = null;
        testItem2 = null;
    }
    @ParameterizedTest
    @ValueSource(strings = {"apple","orange"})
    public void updateInventoryTest(String itemID){

        try {
            int expResults = instanceToTest.getItemById(itemID).getQuantity() - 2;
            instanceToTest.updateInventory(boughtItemsTest.getSaleDetails());
            StoreItem item = instanceToTest.getItemById(itemID);
            assertEquals(expResults, item.getQuantity());;
        }catch (InvalidItemException e){
            fail("Failed to update inventory");
        }



    }
    @Test
    public void updateInventoryRemoveItemTest(){
        String itemID = "apple";
        testItem1.setQuantity(8);
        try {
            instanceToTest.updateInventory(boughtItemsTest.getSaleDetails());
            boolean results = instanceToTest.checkItem(itemID);
            boolean expResult = false;
            assertEquals(expResult, results );
        }
        catch (InvalidItemException e){
            fail("Error: Wrong item ID");
        }



    }
    @Test
    public void testRemoveItem() {
        String removeTest = "apple";
        instanceToTest.removeItem(removeTest);
        boolean expResult = false;
        assertEquals(expResult, instanceToTest.checkItem(removeTest));

    }
    @ParameterizedTest
    @ValueSource(strings = {"apple","orange", "BigWheel Oatmeal"})
    public void testGetItemById(String itemID) throws InvalidItemException {
        boolean expResult = true;
        try{
            assertEquals(expResult, instanceToTest.getItemById(itemID) instanceof StoreItem && instanceToTest.getItemById(itemID).getItemID().equals(itemID));
        }
        catch (InvalidItemException e){
            fail("Invalid item");
        }


    }
    @Test
    public void testGetItemByWrongId(){
        String testID = "HDHH";
        try {
            instanceToTest.getItemById(testID);
            fail("fetch item even though it isn't in inventory");
        }
        catch (InvalidItemException e){
            assertTrue(e.getMessage().contains("ERROR"));
        }


    }
    @Test
    public void testDatabaseNotRunning(){
        instanceToTest.setInventoryStatus("offline");
        try {
            instanceToTest.getItemById("apple");
            fail("Could fetch Item");
        } catch (DatabaseUnavailableException | InvalidItemException e) {
           assertTrue(e.getMessage().contains("ERROR"));
        }
    }


}
