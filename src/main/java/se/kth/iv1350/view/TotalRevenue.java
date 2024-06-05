package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

public abstract class TotalRevenue implements SaleObserver {
    protected Amount totRevenue;
    protected TotalRevenue() {
        totRevenue = new Amount(0);
    }
    private void calculateTotalIncome(Amount priceOfTheSaleThatWasJustMade){
        totRevenue = totRevenue.addition(priceOfTheSaleThatWasJustMade);
    }
    @Override
    public void newSale( Amount paidAmount ) {
        calculateTotalIncome(paidAmount); // Calculate the total amount paid since the program started.
        showTotalIncome();
    }

    private void showTotalIncome () {
       try {
            doShowTotalIncome();
       }
       catch ( Exception e ) {

             handleErrors ( e );
       }
    }
    protected abstract void doShowTotalIncome () throws Exception ;
    protected abstract void handleErrors ( Exception e );

}
