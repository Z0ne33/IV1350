package se.kth.iv1350.integration;

import se.kth.iv1350.model.Sale;

import java.util.ArrayList;

public class AccountingSystem {
    private ArrayList<Sale> sales;
    private static final AccountingSystem INSTANCE = new AccountingSystem();
    private int accountingStatus;
    /**
     * Is used for creating a new Accounting object
     */
    private AccountingSystem(){
        sales = new ArrayList<>();
        setAccountingStatus("online");
    }
    public static AccountingSystem getInstance(){
        return INSTANCE;
    }


    /**
     * updates the Arraylist that keeps track of all Sales
     *
     * @param sale is the Sale object that was sent from contr and is added to the Arraylist
     */
    public void updateAccounting(Sale sale){
        if (accountingStatus == 1){
            sales.add(sale);
        }
        else{
            throw new DatabaseUnavailableException("ERROR: Accounting System is currently unavailable try again later");
        }

    }

    /**
     * Sets the availability for the database
     *
     * @param status is a string that indicates if it is online or offline
     */
    public void setAccountingStatus( String status ) {
        if (status.toLowerCase().equals("online"))
            this.accountingStatus = 1;
        if (status.equals("offline")){
            this.accountingStatus = 0;
        }
    }

    /**
     *  returns sale
     */
    public ArrayList<Sale> showAccounting(){
        if (accountingStatus == 1){
            return sales;

        }
        throw new DatabaseUnavailableException("ERROR : Accounting System is currently unavailable try again later");

    }
}
