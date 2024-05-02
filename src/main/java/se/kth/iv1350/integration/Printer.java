package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;

public class Printer
{
    /**
     * function prints reciepts
     *
     * @param receipt is the reciept that will be printed
     */
    public void PrintReciept( Receipt receipt){
        System.out.println(receipt.createReceiptString());
    }
}
