package se.kth.iv1350.integration;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;
import se.kth.iv1350.util.ErrorLogger;
import se.kth.iv1350.util.WarningLevel;
import se.kth.iv1350.view.ErrorMessageHandler;
import se.kth.iv1350.view.TotalRevenue;
import se.kth.iv1350.view.TotalRevenueView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput extends TotalRevenue {
    private PrintWriter logStream;
    private ErrorLogger errorLogger;

    /**
     * Constructor is used to create and instantiate TotalRevenueFileOutput
     */
    public TotalRevenueFileOutput(){
        errorLogger = new ErrorLogger();
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
    protected void doShowTotalIncome() throws Exception {
        log("Total Revenue: " + totRevenue.getAmount() + (totRevenue.getCurrency() != null ? ", Currency: " + totRevenue.getCurrency() : ""));
    }

    @Override
    protected void handleErrors( Exception e ) {
       errorLogger.log(WarningLevel.WARNING, "an error occurred during the handling of total revenue", e);
    }

    /**
     * function prints a message a file
     * @param message function takes in a message
     */
    private void log(String message){
        logStream.println(message);
    }
}
