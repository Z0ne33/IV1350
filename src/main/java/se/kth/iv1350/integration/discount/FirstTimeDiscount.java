package se.kth.iv1350.integration.discount;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;

public class FirstTimeDiscount implements Discount{
    private double rateOfDiscount = 0.3;

    /**
     * Calculates the discount for First time customers and that is 0.3 reduction for the entire sale
     *
     * @param saleDetails is the details for the Sale
     */
    @Override
    public Amount discount( SaleDTO saleDetails ) {
        for (String customers : saleDetails.getCostumers() ) {
            if (saleDetails.getCurrentCustomer() == customers ){
                return new Amount(0);
            }

        }
       // 287 - (287 * 0.3)
        return new Amount(saleDetails.getTotalPrice().getAmount() * rateOfDiscount);
    }
}
