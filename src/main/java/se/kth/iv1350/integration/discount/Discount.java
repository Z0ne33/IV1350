package se.kth.iv1350.integration.discount;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;

/**
 * Is used to implement the strategy pattern,
 * is the interface which the different strategies will inherit from
 */
public interface Discount {



    /**
     * Calculates the total sum of discount
     *
     * @param saleDetails is the details during the sale
     * @return an Amount that will be reduced from sale
     */
    Amount discount( SaleDTO saleDetails );
}
