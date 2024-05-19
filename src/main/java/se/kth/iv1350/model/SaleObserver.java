package se.kth.iv1350.model;

import se.kth.iv1350.model.DTO.ItemDTO;


/**
 * A listener interface for receiving notifications about total sum of sale. The class that is interested
 * in such notifications implements this interface.
 */
public interface SaleObserver {

    /**
     * Invoked when a sale has been paid.
     *
     * @param sumTotalCost is the total sum of sale
     */
    void newSale( Amount sumTotalCost );
}
