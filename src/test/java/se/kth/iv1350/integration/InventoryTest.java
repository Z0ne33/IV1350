package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.StoreItem;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private InventorySystem instanceToTest;
    private StoreItem testItem1;
    private StoreItem testItem2;
    private Collection<StoreItem> boughtItemsTest;
    @BeforeEach
    public void setUp() {
        instanceToTest = new InventorySystem();
        boughtItemsTest = new ArrayList<>();
        instanceToTest.createItem();
        testItem1 = new StoreItem(null,"apple",  2, 0.25);
        testItem2 = new StoreItem(null, "orange", 2, 0.25);
        boughtItemsTest.add(testItem1);
        boughtItemsTest.add(testItem2);

    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        testItem1 = null;
        testItem2 = null;
        boughtItemsTest = null;
    }
    @ParameterizedTest
    @ValueSource(strings = {"apple","orange"})
    public void updateInventoryTest(String itemID) {
        int expResults = instanceToTest.getItemById(itemID).getQuantity() - 2;
        instanceToTest.updateInventory(boughtItemsTest);
        StoreItem item = instanceToTest.getItemById(itemID);
        assertEquals(expResults, item.getQuantity());

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
    public void testGetItemById(String itemID) {
        boolean expResult = true;
        assertEquals(expResult, instanceToTest.getItemById(itemID) instanceof StoreItem && instanceToTest.getItemById(itemID).getItemID().equals(itemID));

    }
    @Test
    public void testGetItemByWrongId() {
        String testID = "HDHH";
        assertNull(instanceToTest.getItemById(testID));

    }

}
