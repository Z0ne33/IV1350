package se.kth.iv1350.integration.discount;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;
import se.kth.iv1350.model.StoreItem;



public class SeniorDiscount implements Discount{
    private double discount = 0;
    /**
     * calculates the discount for Senior Discount and that is 1 for every item that is bought
     *
     * @param saleDetails is the details for the Sale
     */
    @Override
    public Amount discount( SaleDTO saleDetails ) {
        for (StoreItem item : saleDetails.getAllItems()) {
            discount += item.getItemDetails().getPrice().getAmount();
        }
        return new Amount(discount);
    }
}
