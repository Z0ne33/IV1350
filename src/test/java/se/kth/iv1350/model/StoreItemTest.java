package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.model.DTO.ItemDTO;

public class StoreItemTest {
    private StoreItem instanceToTest1;
    private StoreItem instanceToTest2;
    private ItemDTO itemDetails1;
    private ItemDTO itemDetails2;

    @BeforeEach
    public void setUp() {

        itemDetails1 = new ItemDTO("testName", new Amount(30, "SEK"), "test Item");
        instanceToTest1 = new StoreItem(itemDetails1,"test ID 1", 3, 0.25 );
        itemDetails2 = new ItemDTO("testName", new Amount(30, "SEK"), "test Item");
        instanceToTest2 = new StoreItem(itemDetails2,"test ID 2", 3, 0.25 );

    }
    @AfterEach
    public void tearDown() {
       itemDetails1 = null;
       itemDetails2 = null;
       instanceToTest1 = null;
       instanceToTest2 = null;

    }
    @Test
    public void itemEqualsTest() {
        boolean expResults = false;
        boolean results = instanceToTest1.equals(instanceToTest2);
        assertEquals(expResults, results);

    }


}
