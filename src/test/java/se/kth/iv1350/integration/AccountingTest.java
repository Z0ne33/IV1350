package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.StoreItem;

import static org.junit.jupiter.api.Assertions.*;

public class AccountingTest {
    private  AccountingSystem instanceToTest;
    private Sale sale;

    @BeforeEach
    public void setUp() {
        instanceToTest = new AccountingSystem();
        sale = new Sale();
    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        sale = null;

    }
    @Test
    public void testIfAccountingWorks() {
        instanceToTest.updateAccounting(sale);
        boolean expResults = true;
        assertEquals(expResults, instanceToTest.showAccounting().contains(sale));

    }

}
