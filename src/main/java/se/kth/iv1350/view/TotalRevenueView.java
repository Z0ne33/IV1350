package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

import static java.lang.System.out;

public class TotalRevenueView implements SaleObserver {

    private  Amount totalRevenue;

    /**
     * constructor for TotalRevenueView
     */
    public TotalRevenueView(){
        totalRevenue = new Amount(0, "SEK");
    }
    /**
     * Overrides the function newSale()
     *
     * @param paidAmount is the amount that was paid during the sale
     */
    @Override
    public void newSale( Amount paidAmount ) {
       totalRevenue =  totalRevenue.addition(paidAmount);
        printCurrentState();
    }
    /**
     * Displays the total revenue
     */
    void printCurrentState(){
        out.println("Total Revenue: " + this.totalRevenue.getAmount() + (this.totalRevenue.getCurrency() != null ? ", Currency: " + this.totalRevenue.getCurrency() : ""));
    }
}
