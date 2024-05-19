package se.kth.iv1350.integration;

import se.kth.iv1350.integration.discount.Discount;
import se.kth.iv1350.integration.discount.EmployeeDiscount;
import se.kth.iv1350.integration.discount.FirstTimeDiscount;
import se.kth.iv1350.integration.discount.SeniorDiscount;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscountDatabase {

    private HashMap<DiscountIDs, Discount> availableDiscount;

    private static final DiscountDatabase INSTANCE = new DiscountDatabase();

    /**
     * Returns the object instance
     */
    public static DiscountDatabase getInstance(){
        return INSTANCE;
    }

    private DiscountDatabase(){
        availableDiscount = new HashMap<>();
        createDiscount();

    }
    private void createDiscount(){
        availableDiscount.put(DiscountIDs.Employee, new EmployeeDiscount());
        availableDiscount.put(DiscountIDs.FirstTime, new FirstTimeDiscount());
        availableDiscount.put(DiscountIDs.Senior, new SeniorDiscount());

    }
    /**
     * Chooses a strategy based on the discountID and uses sale details to calculate it
     *
     * @param id is the discount id that the cashier recieved
     * @param saleDetails are the details present in the sale
     */
    public Amount getDiscountByID(SaleDTO saleDetails, DiscountIDs id){

        return availableDiscount.get(id).discount(saleDetails);

    }

}
