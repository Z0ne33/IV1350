package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

public abstract class TotalRevenue implements SaleObserver {
    protected Amount totRevenue;
    protected ErrorMessageHandler errorMessageHandler;
    protected TotalRevenue() {
        totRevenue = new Amount(0);

    }
    @Override
    public void newSale( Amount paidAmount ) {
        totRevenue =  totRevenue.addition(paidAmount);
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
