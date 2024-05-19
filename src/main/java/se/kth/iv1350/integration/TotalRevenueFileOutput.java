package se.kth.iv1350.integration;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;
import se.kth.iv1350.view.TotalRevenueView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements SaleObserver {
    private PrintWriter logStream;
    private Amount totalRevenue;

    /**
     * Constructor is used to create and instantiate TotalRevenueFileOutput
     */
    public TotalRevenueFileOutput(){
        totalRevenue = new Amount(0, "SEK");
        try{
            logStream = new PrintWriter(new FileWriter("revenueLog.txt"), true);
        }
        catch (IOException ioe){
            System.out.println("Failed to log");
            ioe.printStackTrace();
        }
    }
    /**
     * Overrides the newSale function which it inherited from SaleObserver
     */
    @Override
    public void newSale( Amount paidAmount ) {
        totalRevenue = totalRevenue.addition(paidAmount);
        log("Total Revenue: " + this.totalRevenue.getAmount() + (this.totalRevenue.getCurrency() != null ? ", Currency: " + this.totalRevenue.getCurrency() : ""));
    }
    /**
     * function prints a message a file
     * @param message function takes in a message
     */
    public void log(String message){
        logStream.println(message);
    }
}
