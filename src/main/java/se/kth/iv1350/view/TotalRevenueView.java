package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

import static java.lang.System.out;

public class TotalRevenueView extends TotalRevenue {

     private ErrorMessageHandler errorMessageHandler;

    /**
     * constructor for TotalRevenueView
     */
    public TotalRevenueView(){
        errorMessageHandler = new ErrorMessageHandler();
    }

    /**
     * Overrides the function newSale()
     *
     * the amount that was paid during the sale
     */

    @Override
    protected void doShowTotalIncome() throws Exception {
        out.println("Total Revenue: " + this.totRevenue.getAmount() + (this.totRevenue.getCurrency() != null ? ", Currency: " + this.totRevenue.getCurrency() : ""));
    }

    @Override
    protected void handleErrors( Exception e ) {
        errorMessageHandler.showErrorMsg("Error: an error occurred during the handling of total revenue" + e.getMessage());
    }

}
