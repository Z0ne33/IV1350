package se.kth.iv1350.integration.discount;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;
import se.kth.iv1350.model.StoreItem;

public class EmployeeDiscount implements Discount{

    private double discount = 0;

    /**
     * Calculates the discount for Employee and that is free oranges
     *
     * @param saleDetails is the details for the Sale
     */
    @Override
    public Amount discount( SaleDTO saleDetails ) {
        for (StoreItem item : saleDetails.getAllItems()) {
            if (item.getItemID() == "orange"){
                discount = item.getItemDetails().getPrice().getAmount() * item.getQuantity();
            }
        }
        return new Amount(discount);
    }
}
